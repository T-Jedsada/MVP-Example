package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.Calculator;
import com.pondthaitay.mvp.example.api.GithubManager;
import com.pondthaitay.mvp.example.base.BasePresenter;
import com.pondthaitay.mvp.example.dao.UserInfoDao;

public class MainPresenter extends BasePresenter<MainView.View> implements MainView.Presenter {

    private GithubManager serviceManager;
    private int resultPlus;

    public static MainView.Presenter create() {
        return new MainPresenter();
    }

    public MainPresenter() {
        serviceManager = GithubManager.getInstance();
    }

    public void setManager(GithubManager manager) {
        serviceManager = manager;
    }

    @Override
    public void plus(int x, int y) {
        resultPlus = Calculator.newInstance().plus(x, y);
        getView().setOnResultPlus(resultPlus);
    }

    @Override
    public void minus(int... number) {
        resultPlus = Calculator.newInstance().minus(number[0], number[1]);
        getView().setOnResultPlus(resultPlus);
    }

    @Override
    public int getResultPlus() {
        return resultPlus;
    }

    @Override
    public void getUserInfo(String username) {
        getView().showProgressDialog();
        GithubManager.getInstance().getUsername(username, new GithubManager.NetworkCallback() {
            @Override
            public void onSuccess(UserInfoDao body) {
                getView().hideProgressDialog();
                getView().showUserInfo(body);
            }

            @Override
            public void onFailure(Throwable t) {
                getView().hideProgressDialog();
                getView().showError(t.getMessage());
            }
        });
    }
}
