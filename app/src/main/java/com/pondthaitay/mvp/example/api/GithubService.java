package com.pondthaitay.mvp.example.api;

import com.pondthaitay.mvp.example.api.base.BaseService;

public class GithubService extends BaseService<GithubApiService>{

    public static GithubService newInstance( String baseUrl ){
        GithubService service = new GithubService();
        service.setBaseUrl( baseUrl );
        return service;
    }

    private GithubService(){
    }

    @Override
    protected Class<GithubApiService> getApiClassType(){
        return GithubApiService.class;
    }
}
