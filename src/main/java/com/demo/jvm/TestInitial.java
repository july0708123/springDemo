package com.demo.jvm;

import java.lang.reflect.Field;

public class TestInitial {
    static int a = 1;
    int b = 2;

    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.demo.jvm.TestInitial");
        Field[] fields = c.getDeclaredFields();
        for(Field f:fields){
            System.out.println("属性名字："+f.getName());
            if(f.get(c) != null){
                System.out.println("属性的值："+f.get(c));
            }else{
                System.out.println("属性的值：null");
            }

        }
    }
}
