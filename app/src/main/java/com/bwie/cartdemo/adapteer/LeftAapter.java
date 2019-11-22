package com.bwie.cartdemo.adapteer;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bwie.cartdemo.ui.ClzActivity;

public class LeftAapter extends RecyclerView.Adapter<LeftAapter.VH> {



    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;



        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemClickListener.onItemClickLisener(v,position);
//                ClzActivity.clzActivity.rightAdapter(position);
            }
        });
    }

    //声明接口的实例
    private ItemClickListener itemClickListener;
    //声明接口类
    public interface ItemClickListener{
        void onItemClickLisener(View view,int pos);
    }
//暴露给调用者（谁调用方法，谁就是调用者）
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class  VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
