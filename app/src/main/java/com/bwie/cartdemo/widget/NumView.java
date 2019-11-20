package com.bwie.cartdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bwie.cartdemo.R;
import com.bwie.cartdemo.ui.CartActivity;

/**
 * 加减器
 */
public class NumView extends LinearLayout {
    int i = 1;//数量默认值1

    public NumView(Context context) {
        super(context);
        init();
    }

    public NumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化资源和数据
     */
    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_plus_layout,this,true);
//        addView(view);

        TextView addTv = view.findViewById(R.id.add);
        TextView jianTv = view.findViewById(R.id.jian);
        EditText editText = view.findViewById(R.id.num);

        addTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                editText.setText(i+"");
                callback.notifyData(i);


            }
        });

        jianTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if (i==0){
                    Toast.makeText(getContext(), "不能再低了", Toast.LENGTH_SHORT).show();
                    i=1;
                }
                editText.setText(i+"");
                callback.notifyData(i);

            }
        });
    }



    private NumCallback callback;

    /**
     * 初始化
     * @param callback
     */
    public void setCallback(NumCallback callback) {
        this.callback = callback;
    }

    public interface NumCallback{
        void notifyData(int i);
    }

}
