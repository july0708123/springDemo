package com.demo.thread;

/**
 * 双重检查机制的单例模式，要加volatile关键字，防止指令重排序。
 * new对象的时候，实际上有3步。1、分配内存空间给对象。2、初始化对象。3、将将对象引用指向内存。
 * 步骤2，3可能会重排序。那么顺序会变成1，3，2.那么线程A执行了1，3.此时线程B来了，确实不为空，但B只拿了一个只有内存空间但未初始化的对象，
 * 所以要加一个volatile关键字，防止指令重排序。
 */
public class DCLSingleton {
    private volatile static DCLSingleton dclSingleton;
    private DCLSingleton(){}
    public static DCLSingleton instance(){
        if(dclSingleton == null){
            synchronized (DCLSingleton.class){
                if(dclSingleton == null){
                    dclSingleton = new DCLSingleton();
                }
            }
        }
        return dclSingleton;
    }
}
