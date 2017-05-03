package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.base.BaseView;

public class MainView {

    public interface View extends BaseView.View {
        void setOnResultPlus(int result);
    }

    public interface Presenter extends BaseView.Presenter<MainView.View> {
        void plus(int x, int y);

        void minus(int... number);

        int getResultPlus();
    }
}