package com.bwie.cartdemo.ui;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.cartdemo.R;
import com.bwie.cartdemo.RetrofitUtils;
import com.bwie.cartdemo.adapteer.CartAdapter;
import com.bwie.cartdemo.api.ICartApiService;
import com.bwie.cartdemo.base.BaseActivity;
import com.bwie.cartdemo.entity.CartBean;
import com.bwie.cartdemo.entity.CartEntity;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends BaseActivity {

    @BindView(R.id.rv_cart)
    XRecyclerView xRecyclerView;

    @BindView(R.id.checkbox)
    CheckBox checkBox;

    @BindView(R.id.totalPrice)
    TextView totalPrice;

    public static CartActivity cartActivity;


    @Override
    protected void initData() {


        cartActivity = this;

        if (true){
            HashMap<String, String> headers = new HashMap<>();
            headers.put("userId", "159");
            headers.put("sessionId", "1574404675724159");

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
        }else{
            List<CartBean> cartBeans = GreendaoUtils.getInstance().getDaoSession().getCartBeanDao().loadAll();
            CartEntity cartEntity = new Gson().fromJson(cartBeans.get(0).json,CartEntity.class);
            cartAdapter = new CartAdapter(this, cartEntity.result);
            xRecyclerView.setAdapter(cartAdapter);
        }



    }

    CartAdapter cartAdapter;

    /**
     * 绘制列表
     *
     * @param cartEntity
     */
    private void fillDatas(CartEntity cartEntity) {



        CartBean cartBean = new CartBean();
        cartBean.json = new Gson().toJson(cartEntity);
        //晴空数据
        GreendaoUtils.getInstance().getDaoSession().getCartBeanDao().deleteAll();
        //插入数据
        GreendaoUtils.getInstance().getDaoSession().getCartBeanDao().insert(cartBean);




        cartAdapter = new CartAdapter(this, cartEntity.result);
        xRecyclerView.setAdapter(cartAdapter);

    }


    @Override
    protected void initView() {

        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartAdapter != null) {
                    //全选的bean类数据修改，ischeked，配合adapter的setchecked处理数据
                    List<CartEntity.Category> categories = cartAdapter.getCategories();
                    if (categories != null && categories.size() > 0) {
                        //一级列表遍历
                        for (CartEntity.Category category : categories) {

                            if (checkBox.isChecked()) {

                                category.isChecked = true;
                            } else {
                                category.isChecked = false;


                            }


                            //二级列表遍历
                            for (CartEntity.Category.Shopping shopping : category.shoppingCartList) {
                                if (checkBox.isChecked()) {
                                    shopping.isChecked = true;
                                } else {
                                    shopping.isChecked = false;

                                }


                            }
                        }
                        cartAdapter.notifyDataSetChanged();
                        totalPrice();
                    }
                }
            }
        });

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void click(View v) {

    }




    /**
     * 计算总价方法
     */
    public void totalPrice() {
        double   total =0;
        StringBuilder sb = new StringBuilder();
        //全选的bean类数据修改，ischeked，配合adapter的setchecked处理数据
        List<CartEntity.Category> categories = cartAdapter.getCategories();
        if (categories != null && categories.size() > 0) {
            //一级列表遍历
            for (CartEntity.Category category : categories) {
                //二级列表遍历
                for (CartEntity.Category.Shopping shopping : category.shoppingCartList) {
                    sb.append(shopping.isChecked+"");
                    if (sb.toString().contains("true")){
                        if (shopping.isChecked){
                            System.out.println("price:"+shopping.price*shopping.num);
                            total+=shopping.price*shopping.num;
                        }

                    }else{
                        total = 0;
                    }






                }
            }
            totalPrice.setText("合计：" + String.valueOf(total));
        }


    }


}
