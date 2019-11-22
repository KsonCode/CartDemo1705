package com.bwie.cartdemo.adapteer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bwie.cartdemo.R;
import com.bwie.cartdemo.entity.CartEntity;
import com.bwie.cartdemo.ui.CartActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 一级adapter，适配器设计模式
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {

    private Context context;
    private List<CartEntity.Category> categories;

    public CartAdapter(Context context, List<CartEntity.Category> list) {
        this.context = context;
        this.categories = list;
    }

    /**
     * 购物车数据
     * @return
     */
    public List<CartEntity.Category> getCategories() {
        return categories;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false);
        VH vh = new VH(view);
        return vh;
    }

    /**
     * 什么时候触发，首次new adapter的时候，只要list集合有数据，或者：手指滑动，或者调用adapter的notifyDataSetChanged
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        if (categories.get(position).isChecked){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }


       CartItemAdapter cartItemAdapter =  new CartItemAdapter(context,categories.get(position).shoppingCartList);

        holder.nameTv.setText(categories.get(position).categoryName);
        holder.rv.setLayoutManager(new LinearLayoutManager(context));
        holder.rv.setAdapter(cartItemAdapter);

        //初始化回调接口对象
        cartItemAdapter.setNotifyCartAdapter(new CartItemAdapter.NotifyCartAdapter() {
            @Override
            public void notify(boolean isChecked) {
//                holder.checkBox.setChecked(isChecked);
                categories.get(position).isChecked = isChecked;
                notifyDataSetChanged();
                CartActivity.cartActivity.totalPrice();
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CartEntity.Category.Shopping shopping : categories.get(position).shoppingCartList) {
                    if (holder.checkBox.isChecked()){
                        shopping.isChecked = true;
                    }else{
                        shopping.isChecked = false;
                    }

                }

                cartItemAdapter.notifyDataSetChanged();

                //
                CartActivity cartActivity = (CartActivity) context;
                cartActivity.totalPrice();

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkBox;
        @BindView(R.id.name)
        TextView nameTv;
        @BindView(R.id.rv_item)
        RecyclerView rv;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
