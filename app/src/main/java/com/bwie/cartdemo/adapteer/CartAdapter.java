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

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.nameTv.setText(categories.get(position).category);
        holder.rv.setLayoutManager(new LinearLayoutManager(context));
//        holder.rv.setAdapter();

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
