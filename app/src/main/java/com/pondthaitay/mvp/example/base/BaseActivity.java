package com.pondthaitay.mvp.example.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pondthaitay.mvp.example.R;
import com.pondthaitay.mvp.example.exception.MvpNotSetLayoutException;
import com.pondthaitay.mvp.example.exception.MvpPresenterNotCreateException;

public abstract class BaseActivity<P extends BaseView.Presenter> extends AppCompatActivity implements BaseView.View {

    private P presenter;
    private ProgressDialog progressDialog;

    protected abstract P createPresenter();

    protected abstract int getLayoutView();

    protected abstract void bindView();

    protected abstract void setupInstance();

    protected abstract void setupView();

    protected abstract void initialize();

    protected abstract void restoreView(Bundle savedInstanceState);

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
        int layoutResId = getLayoutView();
        if (getLayoutView() == 0) throw new MvpNotSetLayoutException();
        setContentView(layoutResId);
        bindView();
        setupInstance();
        setupView();
        setupProgressDialog();
        getPresenter().onViewCreate();
        if (savedInstanceState == null) initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onViewStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onViewStop();
    }

    @Override
    protected void onDestroy() {
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreView(savedInstanceState);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null) progressDialog.dismiss();
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.loading));
    }

}
