package com.bwie.cartdemo.adapteer;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RightAapter extends RecyclerView.Adapter<RightAapter.VH> {


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

            }
        });
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
