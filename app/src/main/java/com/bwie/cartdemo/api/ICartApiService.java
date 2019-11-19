package com.bwie.cartdemo.api;

import com.bwie.cartdemo.entity.CartEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface ICartApiService {

    @GET("small/order/verify/v1/findShoppingCart")
    Observable<CartEntity> getCarts(@HeaderMap HashMap<String,String> headers);
}
