package com.pondthaitay.mvp.example.ui;

import android.support.annotation.NonNull;

import com.pondthaitay.mvp.example.api.GithubServiceManager;
import com.pondthaitay.mvp.example.base.BasePresenter;
import com.pondthaitay.mvp.example.dao.UserInfoDao;

class MainPresenter extends BasePresenter<MainInterface.View>
        implements MainInterface.Presenter {

    private GithubServiceManager serviceManager;

    void setManager(GithubServiceManager manager) {
        serviceManager = manager;
    }

    public static MainInterface.Presenter create() {
        return new MainPresenter();
    }

    MainPresenter() {
        this.serviceManager = GithubServiceManager.getInstance();
    }

    @Override
    public void getUserInfo(@NonNull String username) {
        if (getView() != null) {
            getView().showProgressDialog();
            serviceManager.requestUserInfo(username, new GithubServiceManager.GithubManagerCallback<UserInfoDao>() {
                @Override
                public void onSuccess(UserInfoDao result) {
                    getView().hideProgressDialog();
                    getView().setUserInfo(result);
                }

                @Override
                public void onFailure(Throwable t) {
                    getView().hideProgressDialog();
                    getView().showError(t.getMessage());
                }
            });
        }
    }
}
