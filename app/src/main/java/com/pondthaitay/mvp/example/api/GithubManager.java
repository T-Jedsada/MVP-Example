package com.pondthaitay.mvp.example.api;

import com.pondthaitay.mvp.example.dao.UserInfoDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubManager {

    private static GithubManager instance;
    private static GithubAPIs api;

    public interface NetworkCallback {
        void onSuccess(UserInfoDao body);

        void onFailure(Throwable t);
    }

    public static GithubManager getInstance() {
        if (instance == null) {
            instance = new GithubManager();
        }
        return instance;
    }

    private GithubManager() {
    }

    public static void setApi(GithubAPIs mockApi) {
        api = mockApi;
    }

    public Call<UserInfoDao> requestCallUserInfo(String username) {
        return GithubService.newInstance("https://api.github.com/")
                .getApi(api)
                .getUserInfo(username);
    }

    public void getUsername(String username, final NetworkCallback callback) {
        requestCallUserInfo(username).enqueue(new Callback<UserInfoDao>() {
            @Override
            public void onResponse(Call<UserInfoDao> call, Response<UserInfoDao> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserInfoDao> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
