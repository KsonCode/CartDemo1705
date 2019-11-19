package com.bwie.cartdemo.entity;

import java.util.List;

public class CartEntity {
    public String message;
    public String status;
    public List<Category> result;

    public static class Category{
        public String categoryName;
        public List<Shopping> shoppingCartList;
        public static class Shopping{

            public String commodityId;
            public String commodityName;
            public String count;
            public String pic;
            public String price;

        }
    }
}
