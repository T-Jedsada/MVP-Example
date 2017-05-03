package com.pondthaitay.mvp.example.ui;

import android.os.Bundle;
import android.util.Log;

import com.pondthaitay.mvp.example.R;
import com.pondthaitay.mvp.example.base.BaseActivity;

public class MainActivity extends BaseActivity<MainView.Presenter> implements MainView.View {

    @Override
    protected void restoreView(Bundle savedInstanceState) {

    }

    @Override
    protected MainView.Presenter createPresenter() {
        return MainPresenter.create();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
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
        getPresenter().plus(5, 5);
        getPresenter().minus(0, 0);
    }

    @Override
    public void setOnResultPlus(int result) {
        Log.e(MainActivity.class.getName(), "result plus : " + result);
    }
}