package com.demo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {
    private static int sum=0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        for(int i=0;i<3;i++){
//            new Thread(()->{
//                //加锁
//                lock.lock();
//                try {
//                    for (int j=0;j<10000;j++){
//                        sum++;
//                    }
//                }finally {
//                    //解锁
//                    lock.unlock();
//                }
//            }).start();
//        }
//        new Thread(()->{
//            lock.lock();
//            try {
//                log.info(""+lock.getHoldCount());
//                sum++;
//                reEnter();
//            }finally {
//                lock.unlock();
//            }
//        }).start();
        Thread t1 = new Thread(()->{
            log.info("t1启动。。。");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("t1在等锁的过程中被中断。。");
            }
        });

        lock.lock();
        try {
            log.info("main线程获取了锁。。");
            t1.start();
            Thread.sleep(100);
            log.info("去中断t1。。");
            t1.interrupt();
        }finally {
            lock.unlock();
        }

        Thread.sleep(3000);
        System.out.println(sum);
    }

    public static void reEnter(){
        lock.lock();
        try {
            log.info(""+lock.getHoldCount());
            sum++;
            log.info("同一把锁，可重入");
        }finally {
            lock.unlock();
        }
    }
}
