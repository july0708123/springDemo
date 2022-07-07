package com.demo.thread;

/**
 * 指令丢弃情况
 */
public class MESIOrderLose {
    volatile long a;

    public static void main(String[] args) throws InterruptedException {
        MESIOrderLose mesi = new MESIOrderLose();
        int count = 10000;
        Thread t1 = new Thread(()->{
            for(int i=0;i<count;i++){
                mesi.a++;
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<count;i++){
                mesi.a++;
            }
        });
        t1.start();;
        t2.start();

        Thread.sleep(5000);
        System.out.println("a="+mesi.a);
    }
}
