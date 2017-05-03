package com.pondthaitay.mvp.example.api;

import android.support.annotation.NonNull;

import com.pondthaitay.mvp.example.dao.UserInfoDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubServiceManager {

    private static GithubServiceManager instance;
    private static GithubApiService api;

    public interface GithubManagerCallback<T> {
        void onSuccess(T result);

        void onFailure(Throwable t);
    }

    public static GithubServiceManager getInstance() {
        if (instance == null)
            instance = new GithubServiceManager();
        return instance;
    }

    public GithubServiceManager() {
    }

    public static void setApi(GithubApiService mockApi) {
        api = mockApi;
    }

    public void requestUserInfo(@NonNull String username, final GithubManagerCallback<UserInfoDao> callback) {
        requestUserInfoCall(username).enqueue(new Callback<UserInfoDao>() {
            @Override
            public void onResponse(Call<UserInfoDao> call, Response<UserInfoDao> response) {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailure(new Throwable("error"));
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoDao> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(t);
                }
            }
        });
    }

    public Call<UserInfoDao> requestUserInfoCall(@NonNull String username) {
        return GithubService.newInstance("https://api.github.com/")
                .getApi(api)
                .getUserInfo(username);
    }
}
