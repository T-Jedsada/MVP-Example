package com.pondthaitay.mvp.example.api;

import com.pondthaitay.mvp.example.dao.UserInfoDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    interface NetworkCallback {
        void onSuccess(UserInfoDao body);

        void onFailure(Throwable t);
    }

    public GithubAPIs callUserInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GithubAPIs.class);
    }


    public void getUsername(String username, final NetworkCallback callback) {
        callUserInfo().getUserInfo(username)
                .enqueue(new Callback<UserInfoDao>() {
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
