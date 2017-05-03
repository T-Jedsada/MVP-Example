package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.base.BaseView;

class MainView {

    interface View extends BaseView.View {
        void setOnResultPlus(int result);
    }

    interface Presenter extends BaseView.Presenter<MainView.View> {
        void plus(int x, int y);

        int getResultPlus();
    }
}