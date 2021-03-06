package com.pondthaitay.mvp.example.ui;


import android.os.Bundle;

import com.pondthaitay.mvp.example.R;
import com.pondthaitay.mvp.example.base.BaseActivity;
import com.pondthaitay.mvp.example.dao.UserInfoDao;

public class MainActivity extends BaseActivity<MainInterface.Presenter>
        implements MainInterface.View {


    @Override
    public MainInterface.Presenter createPresenter() {
        return MainPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }


    @Override
    public void bindView() {

    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
    }

    @Override
    public void initialize() {
        getPresenter().getUserInfo("pondthaitay");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void setUserInfo(UserInfoDao dao) {

    }

    @Override
    public void showError(String message) {

    }
}