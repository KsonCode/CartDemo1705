package com.bwie.cartdemo.api;

import com.bwie.cartdemo.entity.ClzBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ClsApiservice {

    @GET
    Observable<ClzBean> getClz();
}
