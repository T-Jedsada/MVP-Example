package com.pondthaitay.mvp.example.template;

import android.os.Bundle;

import com.pondthaitay.mvp.example.base.BaseActivity;

public class CustomActivity extends BaseActivity<CustomInterface.Presenter> implements CustomInterface.View {
    @Override
    public void showTest(int result) {

    }

    @Override
    protected CustomInterface.Presenter createPresenter() {
        return CustomPresenter.create();
    }

    @Override
    protected int getLayoutView() {
        return 0;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void setupInstance() {

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void restoreView(Bundle savedInstanceState) {

    }
}
