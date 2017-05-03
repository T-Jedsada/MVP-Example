package com.pondthaitay.mvp.example.template;

import com.pondthaitay.mvp.example.base.BaseView;

class CustomInterface {

    public interface View extends BaseView.View {
        void showTest(int result);
    }

    public interface Presenter extends BaseView.Presenter<CustomInterface.View> {
        void intitValue(int result);
    }
}
