package com.pondthaitay.mvp.example.api;

import com.pondthaitay.mvp.example.dao.UserInfoDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiService {

    @GET("users/{username}")
    Call<UserInfoDao> getUserInfo(@Path("username") String username);
}
