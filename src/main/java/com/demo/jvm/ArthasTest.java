package com.demo.jvm;

import java.util.HashSet;

public class ArthasTest {
    private static HashSet hashSet = new HashSet();

    public static void main(String[] args) {
        //模拟cpu过高
        cpuHigh();
        //模拟死锁
        deadThread();
        //不断向hashSet集合增加数据
        addHashSetThread();
    }

    public static void cpuHigh(){
        new Thread(()->{
            while (true){

            }
        }).start();
    }

    public static void deadThread(){
        Object locak1 = new Object();
        Object locak2 = new Object();
        new Thread(()->{
            synchronized (locak1){
                System.out.println("thread1 begin");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (locak2){
                    System.out.println("thread1 end");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (locak2){
                System.out.println("thread2 begin");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (locak1){
                    System.out.println("thread2 end");
                }
            }
        }).start();
    }

    public static void addHashSetThread(){
        new Thread(()->{
            int count = 0;
            while (true){
                try {
                    hashSet.add("count"+count);
                    count++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
