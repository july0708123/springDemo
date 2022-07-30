package com.demo.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedTest {
    static int sum;
    //加在静态方法上，锁住的是整个类
    public synchronized static void increamSum(){
        sum++;
    }
    public synchronized static void decreamSum(){
        sum--;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(int i=0;i<5000;i++){
                increamSum();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<5000;i++){
                decreamSum();
            }
        });
        t1.start();
        t2.start();
        t1.join(); // join():让主线程等待，直到这个线程结束（因为有个while(true)一直在循环）
        t2.join();
        log.info("{}",sum);
    }
}
