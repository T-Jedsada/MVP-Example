package com.pondthaitay.mvp.example.template.fragment;

import com.pondthaitay.mvp.example.base.BaseInterface;

public class CustomFragmentInterface{


    public interface View extends BaseInterface.View{
    }

    public interface Presenter extends BaseInterface.Presenter<CustomFragmentInterface.View>{
    }
}
