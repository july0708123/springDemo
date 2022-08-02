package com.demo.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;

/**
 * CyclicBarrier(回环栅栏)允许一组线程互相等待，直到到达某个公共屏障点 (Common Barrier Point)
 * 闭锁用于等待countDown事件，而栅栏用于等待其他线程。
 *
 */
@Slf4j
public class CyclicBarrierTest {

    static int sum = 0;
    //static LongAdder sum = new LongAdder();

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                //如果想汇总，应该把await放到业务逻辑的后边
                System.out.println("我要汇总，我先执行吗？");
            }
        });


        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.debug(Thread.currentThread().getName() + "开始等待其他线程");
                        cyclicBarrier.await();
                        log.debug(Thread.currentThread().getName() + "开始执行");
                        //TODO 模拟业务处理
                        for (int i=0;i<10000;i++){
                            sum++;
                            //sum.increment();
                        }
                        log.debug(Thread.currentThread().getName() + "执行完毕,sum="+sum);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}

