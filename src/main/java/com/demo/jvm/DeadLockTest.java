package com.demo.jvm;

public class DeadLockTest {
    private static Object locak1 = new Object();
    private static Object locak2 = new Object();

    public static void main(String[] args) {
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

        System.out.println("main end");
    }
}
