package com.pondthaitay.mvp.example.api;

import com.pondthaitay.mvp.example.api.base.BaseService;

/**
 * Created by jedsada on 5/3/17.
 */

public class GithubService extends BaseService<GithubAPIs> {

    public static GithubService newInstance(String baseUrl) {
        GithubService service = new GithubService();
        service.setBaseUrl(baseUrl);
        return service;
    }

    @Override
    protected Class<GithubAPIs> getApiClassType() {
        return null;
    }
}
