package com.pondthaitay.mvp.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pondthaitay.mvp.example.exception.MvpNotSetLayoutException;
import com.pondthaitay.mvp.example.exception.MvpPresenterNotCreateException;

public abstract class BaseFragment<P extends BaseView.Presenter> extends Fragment implements BaseView.View {

    private P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutView();

    protected abstract void bindView(View view);

    protected abstract void setupInstance();

    protected abstract void setupView();

    protected abstract void initialize();

    protected abstract void restoreView(Bundle savedInstanceState);

    protected abstract void onRestoreInstanceState(Bundle savedInstanceState);

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
        if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() == 0) throw new MvpNotSetLayoutException();
        return inflater.inflate(getLayoutView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupInstance();
        setupView();
        getPresenter().onViewCreate();
        if (savedInstanceState == null) initialize();
        else restoreView(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onViewStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onViewStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onViewDestroy();
        presenter.detachView();
    }

    @Override
    public P getPresenter() {
        if (presenter != null) return presenter;
        throw new MvpPresenterNotCreateException();
    }
}
