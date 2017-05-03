package com.pondthaitay.mvp.example.template.fragment;

import com.pondthaitay.mvp.example.base.BasePresenter;

public class CustomFragmentPresenter extends BasePresenter<CustomFragmentInterface.View>
        implements CustomFragmentInterface.Presenter {

    public static CustomFragmentInterface.Presenter create() {
        return new CustomFragmentPresenter();
    }

}
