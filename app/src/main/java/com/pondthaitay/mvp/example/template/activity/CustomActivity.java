package com.pondthaitay.mvp.example.template.activity;


import android.os.Bundle;

import com.pondthaitay.mvp.example.base.BaseActivity;

public class CustomActivity extends BaseActivity<CustomActivityInterface.Presenter>
        implements CustomActivityInterface.View {


    @Override
    public CustomActivityInterface.Presenter createPresenter() {
        return CustomActivityPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return 0;
    }


    @Override
    public void bindView() {
        getPresenter().test();
    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
    }

    @Override
    public void initialize() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}

