package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.base.BaseInterface;
import com.pondthaitay.mvp.example.dao.UserInfoDao;

class MainInterface {


    public interface View extends BaseInterface.View {

        void setUserInfo(UserInfoDao dao);

        void showError(String message);
    }

    public interface Presenter extends BaseInterface.Presenter<MainInterface.View> {

        void getUserInfo(String username);
    }
}
