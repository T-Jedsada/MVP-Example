package com.pondthaitay.mvp.example.api;

import com.pondthaitay.mvp.example.dao.UserInfoDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubServiceManager {

    private static GithubServiceManager instance;
    private static GithubApiService api;

    public interface GithubManagerCallback<T> {
        void onSuccess(UserInfoDao dao);

        void onFailure(Throwable t);
    }

    public static GithubServiceManager getInstance() {
        if (instance == null) {
            instance = new GithubServiceManager();
        }
        return instance;
    }

    private GithubServiceManager() {
    }

    public static void setApi(GithubApiService mockApi) {
        api = mockApi;
    }


    public void requestUserInfo(String username, final GithubManagerCallback callback) {
        requestUserInfoCall(username).enqueue(new Callback<UserInfoDao>() {
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

    public Call<UserInfoDao> requestUserInfoCall(String username) {
        return GithubService.newInstance("https://api.github.com/")
                .getApi(api)
                .getUserInfo(username);
    }
}
