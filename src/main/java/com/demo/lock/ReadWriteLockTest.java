package com.demo.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 */

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        final Queue queue = new Queue();


        //cpu时间片分配的关系，读写获取锁的先后顺序不固定。公平锁也就是一定意义上的公平：假如有在阻塞排队的线程，你也去排队阻塞，不能优先抢锁。
        //非公平状态下，也不用担心写线程获取不到锁。因为可能有些读线程还没分配到时间片，写线程分配到了，恰好此时又没有其他线程占用着读锁和写锁，那这个写线程就可以抢到写锁。

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.put(new Random().nextInt(10000));
                }
            }).start();
        }

        //Thread.sleep(100);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                        queue.get();
                }
            }).start();
        }


    }
}

@Slf4j
class Queue {
    //共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
    private Object data = null;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void get() {
        log.debug(Thread.currentThread().getName() + " be ready to read data!");
        //上读锁，其他线程只能读不能写
        readLock.lock();
        log.debug(Thread.currentThread().getName() + " 获取到了读锁");
        try {
            Thread.sleep(1000);
            log.debug(Thread.currentThread().getName() + " have read data :" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放读锁
            readLock.unlock();
        }
    }

    public void put(Object data) {
        log.debug(Thread.currentThread().getName() + " be ready to write data!");
        //上写锁，不允许其他线程读也不允许写
        writeLock.lock();
        log.debug(Thread.currentThread().getName() + " 获取到了写锁");
        try {
            Thread.sleep(5000);
            this.data = data;
            log.debug(Thread.currentThread().getName() + " have write data: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放写锁
            writeLock.unlock();
        }
    }
}