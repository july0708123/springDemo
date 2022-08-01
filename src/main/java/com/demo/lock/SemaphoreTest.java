package com.demo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * Semaphore是一个计数信号量,Semaphore经常用于限制获取资源的线程数量
 *
 */
@Slf4j
public class SemaphoreTest {
    static int sum = 0;
    public static void main(String[] args) {
        // 声明3个窗口  state:  资源数
        Semaphore windows = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 占用窗口    加锁
                        windows.acquire();
                        log.debug(Thread.currentThread().getName() + ": 开始买票");
                        //模拟买票流程
                        //sum还是存在并发问题，使用longAdder可以解决
                        for(int i=0;i<10000;i++){
                            sum++;
                        }
                        //Thread.sleep(5000);
                        log.debug(Thread.currentThread().getName() + ": 购票成功"+",sum="+sum);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 释放窗口
                        windows.release();
                    }
                }
            }).start();

        }
    }
}