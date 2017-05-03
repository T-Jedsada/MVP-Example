package com.pondthaitay.mvp.example.template.activity;

import com.pondthaitay.mvp.example.base.BasePresenter;

class CustomActivityPresenter extends BasePresenter<CustomActivityInterface.View>
        implements CustomActivityInterface.Presenter {

    public static CustomActivityInterface.Presenter create() {
        return new CustomActivityPresenter();
    }

    @Override
    public void test() {

    }
}
