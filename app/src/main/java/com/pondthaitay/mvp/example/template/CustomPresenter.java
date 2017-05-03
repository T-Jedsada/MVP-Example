package com.pondthaitay.mvp.example.template;

import com.pondthaitay.mvp.example.base.BasePresenter;

class CustomPresenter extends BasePresenter<CustomInterface.View> implements CustomInterface.Presenter {

    static CustomPresenter create(){
        return new CustomPresenter();
    }

    @Override
    public void intitValue(int result) {
        getView().showTest(result);
    }
}
