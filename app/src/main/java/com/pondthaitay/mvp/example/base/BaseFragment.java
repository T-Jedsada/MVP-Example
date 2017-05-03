package com.pondthaitay.mvp.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pondthaitay.mvp.example.exception.MvpNotSetLayoutException;
import com.pondthaitay.mvp.example.exception.MvpPresenterNotCreateException;

public abstract class BaseFragment<P extends BaseInterface.Presenter>
        extends Fragment
        implements BaseInterface.View {

    private P presenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
        if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutResId = getLayoutView();
        if (getLayoutView() == 0) throw new MvpNotSetLayoutException();
        return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupInstance();
        setupView();
        getPresenter().onViewCreate();
        if (savedInstanceState == null) {
            initialize();
        } else {
            restoreView(savedInstanceState);
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public abstract P createPresenter();

    public abstract int getLayoutView();

    public abstract void bindView(View view);

    public abstract void setupInstance();

    public abstract void setupView();

    public abstract void initialize();

    public void restoreView(Bundle savedInstanceState) {
    }
}

