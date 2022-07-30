package com.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            new Thread(()->{
                for(int j=0;j<10000;j++){
                    //基于CAS自旋的自增操作
                    sum.incrementAndGet();
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum.get());
    }
}
