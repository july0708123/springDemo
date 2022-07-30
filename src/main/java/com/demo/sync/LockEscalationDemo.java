package com.demo.sync;

import org.openjdk.jol.info.ClassLayout;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Fox
 * 测试 偏向锁，轻量级锁，重量级锁标记变化
 * 关闭延迟开启偏向锁： -XX:BiasedLockingStartupDelay=0
 * 无锁 001
 * 偏向锁 101
 * 轻量级锁 00
 * 重量级锁 10
 */
@Slf4j
public class LockEscalationDemo{

    public static void main(String[] args) throws InterruptedException {

//        log.debug(ClassLayout.parseInstance(new Object()).toPrintable());
        //HotSpot 虚拟机在启动后有个 4s 的延迟才会对每个新建的对象开启偏向锁模式
        Thread.sleep(5000);
        Object obj = new Object();
        // 思考： 如果对象调用了hashCode,还会开启偏向锁模式吗
        //不会，偏向锁没有记录hashCode的地方，会撤销变回无锁状态
//        obj.hashCode();
        log.debug("4秒之后创建的对象。。。"+ClassLayout.parseInstance(obj).toPrintable());

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug(Thread.currentThread().getName()+"开始执行去争抢锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
//                for(int i=1;i<=3;i++){
//                    synchronized (obj){
//                        log.debug(Thread.currentThread().getName()+"第"+i+"获取到了锁，执行中。。。\n"
//                                +ClassLayout.parseInstance(obj).toPrintable());
//                    }
                synchronized (obj){
                    log.debug(Thread.currentThread().getName()+"获取到了锁，执行中。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                    // 思考：偏向锁执行过程中，调用hashcode会发生什么？已偏向锁强制升级为重量级锁
//                    obj.hashCode();
//                    //obj.notify();
////                    try {
////                        obj.wait(50);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug(Thread.currentThread().getName()+"获取到了锁，sleep3秒后执行了。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                }
                log.debug(Thread.currentThread().getName()+"释放锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
            }
        },"thread1").start();

        //控制线程竞争时机
        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug(Thread.currentThread().getName()+"开始执行去争抢锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
                synchronized (obj){

                    log.debug(Thread.currentThread().getName()+"获取到了锁，执行中。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                }
                log.debug(Thread.currentThread().getName()+"释放锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
            }
        },"thread2").start();
//
//        Thread.sleep(1000);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.debug(Thread.currentThread().getName()+"开始执行。。。\n"
//                        +ClassLayout.parseInstance(obj).toPrintable());
//                synchronized (obj){
//
//                    log.debug(Thread.currentThread().getName()+"获取锁执行中。。。\n"
//                            +ClassLayout.parseInstance(obj).toPrintable());
//                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.debug(Thread.currentThread().getName()+"释放锁。。。\n"
//                        +ClassLayout.parseInstance(obj).toPrintable());
//            }
//        },"thread3").start();


        Thread.sleep(5000);
        log.debug(ClassLayout.parseInstance(obj).toPrintable());



    }
}