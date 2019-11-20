package com.bwie.cartdemo.entity;

import java.util.List;

public class CartEntity {
    public String message;
    public String status;
    public List<Category> result;//一级

    public static class Category{
        public boolean isChecked;//一级是否选中的标示
        public String categoryName;
        public List<Shopping> shoppingCartList;//二级
        public static class Shopping{

            public boolean isChecked;//二级是否选中的标示
            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public double price;
            public int num =1;//控制加减器数量的属性

        }
    }
}
