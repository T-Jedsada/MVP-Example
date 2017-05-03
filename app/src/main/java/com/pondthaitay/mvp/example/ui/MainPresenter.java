package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.base.BasePresenter;

class MainPresenter extends BasePresenter<MainView.View> implements MainView.Presenter {

    private int resultPlus;

    static MainView.Presenter create() {
        return new MainPresenter();
    }

    @Override
    public void plus(int x, int y) {
        resultPlus = x + y;
        getView().setOnResultPlus(resultPlus);
    }

    @Override
    public int getResultPlus() {
        return resultPlus;
    }
}
