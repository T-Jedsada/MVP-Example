package com.pondthaitay.mvp.example.template.activity;

import com.pondthaitay.mvp.example.base.BaseInterface;

public class CustomActivityInterface{


    public interface View extends BaseInterface.View{
    }

    public interface Presenter extends BaseInterface.Presenter<CustomActivityInterface.View>{

        void test();
    }
}
