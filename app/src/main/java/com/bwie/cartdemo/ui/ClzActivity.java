package com.bwie.cartdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.bwie.cartdemo.MainActivity;
import com.bwie.cartdemo.R;
import com.bwie.cartdemo.RetrofitUtils;
import com.bwie.cartdemo.adapteer.LeftAapter;
import com.bwie.cartdemo.adapteer.RightAapter;
import com.bwie.cartdemo.api.ClsApiservice;
import com.bwie.cartdemo.base.BaseActivity;
import com.bwie.cartdemo.entity.ClzBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class ClzActivity extends BaseActivity implements LeftAapter.ItemClickListener, View.OnClickListener {

    @BindView(R.id.left)
    XRecyclerView recyclerView;
    @BindView(R.id.right)
    XRecyclerView recyclerView2;

    public static ClzActivity clzActivity;

    private ClzBean cBean;
    LeftAapter leftAapter;

    @Override
    protected void initData() {
        clzActivity = this;
        RetrofitUtils.getInstance().createService(ClsApiservice.class)
                .getClz().subscribe(new Consumer<ClzBean>() {
            @Override
            public void accept(ClzBean clzBean) throws Exception {

               cBean = clzBean;

//                leftAapter = new LeftAapter(cBean.result);
//
//                leftAapter.setItemClickListener(ClzActivity.this);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });



    }

    public void rightAdapter(int i){
//        RightAapter rightAapter = new RightAapter(cBean.result.get(i).secondCategoryVo);
//        recyclerView2.setAdapter(rightAapter);
    }
    @Override
    protected void initView() {

//        recyclerView2.setOnClickListener(this);

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_clz;
    }

    @Override
    protected void click(View v) {

    }

    /**
     * 点击左侧某个条目的点击事件
     * @param view
     * @param pos
     */
    @Override
    public void onItemClickLisener(View view, int pos) {
//        RightAapter rightAapter = new RightAapter(cBean.result.get(pos).secondCategoryVo);
//        recyclerView2.setAdapter(rightAapter);

    }
}
