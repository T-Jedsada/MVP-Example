package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.base.BaseView;
import com.pondthaitay.mvp.example.dao.UserInfoDao;

public class MainView {

    public interface View extends BaseView.View {
        void setOnResultPlus(int result);

        void showUserInfo(UserInfoDao body);

        void showError(String message);
    }

    public interface Presenter extends BaseView.Presenter<MainView.View> {
        void plus(int x, int y);

        void minus(int... number);

        int getResultPlus();

        void getUserInfo(String username);
    }
}