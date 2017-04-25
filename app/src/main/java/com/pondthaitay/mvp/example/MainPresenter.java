package com.pondthaitay.mvp.example;

import com.pondthaitay.mvp.example.base.BasePresenter;

class MainPresenter extends BasePresenter<MainView.View> implements MainView.Presenter {

    static MainView.Presenter create() {
        return new MainPresenter();
    }

    @Override
    public void plus(int x, int y) {
        getView().setOnResultPlus(x + y);
    }
}
