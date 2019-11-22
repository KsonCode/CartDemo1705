package com.bwie.cartdemo.entity;

import java.util.List;

public class ClzBean {

    public String message;
    public String status;
    public List<Clz> result;
    public static class Clz{
        public String id;
        public String name;
        public List<Second> secondCategoryVo;

        public static class Second{
            public String id;
            public String name;
        }

    }
}
