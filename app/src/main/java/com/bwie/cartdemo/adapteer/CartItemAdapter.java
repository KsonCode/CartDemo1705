package com.bwie.cartdemo.adapteer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bwie.cartdemo.R;
import com.bwie.cartdemo.entity.CartEntity;
import com.bwie.cartdemo.ui.CartActivity;
import com.bwie.cartdemo.widget.NumView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 二级adapter，适配器设计模式
 */
public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.VH>  {

    private Context context;
    private List<CartEntity.Category.Shopping> shoppingList;


    public CartItemAdapter(Context context, List<CartEntity.Category.Shopping> list) {
        this.context = context;
        this.shoppingList = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_subitem_layout, parent, false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        if (shoppingList.get(position).isChecked) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        Glide.with(context).load(shoppingList.get(position).pic).into(holder.iv);
        holder.name.setText(shoppingList.get(position).commodityName);
        holder.price.setText("¥：" + shoppingList.get(position).price);


        holder.numView.setCallback(new NumView.NumCallback() {
            @Override
            public void notifyData(int i) {
                shoppingList.get(position).num = i;
                CartActivity.cartActivity.totalPrice();
            }

        });

    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }



    static class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkBox;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.numView)
        NumView numView;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
