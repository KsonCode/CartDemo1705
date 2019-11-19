package com.bwie.cartdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.bwie.cartdemo.R;
import com.bwie.cartdemo.RetrofitUtils;
import com.bwie.cartdemo.adapteer.CartAdapter;
import com.bwie.cartdemo.api.ICartApiService;
import com.bwie.cartdemo.base.BaseActivity;
import com.bwie.cartdemo.entity.CartEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends BaseActivity {

    @BindView(R.id.rv_cart)
    XRecyclerView xRecyclerView;


    @Override
    protected void initData() {

        HashMap<String,String>  headers = new HashMap<>();
        headers.put("userId","159");
        headers.put("sessionId","1574135048990159");

        RetrofitUtils.getInstance().createService(ICartApiService.class)
                .getCarts(headers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CartEntity>() {
                    @Override
                    public void accept(CartEntity cartEntity) throws Exception {

                        fillDatas(cartEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }

    CartAdapter cartAdapter ;
    /**
     * 绘制列表
     * @param cartEntity
     */
    private void fillDatas(CartEntity cartEntity) {


        cartAdapter = new CartAdapter(this,cartEntity.result);
        xRecyclerView.setAdapter(cartAdapter);

    }

    @Override
    protected void initView() {

        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void click(View v) {

    }
}
