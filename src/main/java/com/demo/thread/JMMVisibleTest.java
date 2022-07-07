package com.demo.thread;

/**
 * 并发三大特性之一：可见性
 * volatile,synchronized,Integer,显性内存屏障
 * 本质：1，内存屏障。2，线程上下文切换
 *
 * 总结：java中可见性如何保证？方式归类有两种：
 * 1、jvm层面storeLoad内存屏障  --> lock addl lock前缀指令
 * 2、线程上下文切换，导致缓存失效，加载上下文时去重新读取主存。典型的有Thread.yield()方法
 */
public class JMMVisibleTest {
    //flag加了volatile关键字，可以跳出循环
    private boolean flag = true;
    //给i加volatile也可以跳出循环？
    // 难道是cpu空间局部性，去主存里读取i的时候，因为读取的是缓存行（64byte），所以把flag也重新读取出来了？
    // 嗯，就是伪共享的情况

    //使用Integer。因为final也可以保证可见性
    private int i=0;

    public void refresh(){
        flag = false;
        System.out.println(Thread.currentThread().getName()+"修改了flag="+flag);
    }

    public void load(){
        System.out.println(Thread.currentThread().getName()+"开始执行。。。");

        while (flag){
            i++;
            //可以跳出循环
            //synchronized(this){};
            //能够跳出循环，释放时间片，一定时间缓存失效。上下文切换，加载上下文，读取主存，flag=true
            //Thread.yield();
            //可以跳出循环，内存屏障
//            try {
//
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //可以跳出循环，有个synchronized
            //System.out.println("i="+i);

            //shortWait(1000000); //等待1ms  此时缓存早已失效，会去主存重新read，可以跳出循环
            //shortWait(1000); //等待1微秒，缓存不失效，不会去重新读取主存，所以跳不出循环

        }
        System.out.println(Thread.currentThread().getName()+"跳出循环i="+i);
    }

    public static void main(String[] args) throws InterruptedException {
        JMMVisibleTest test = new JMMVisibleTest();
        Thread t1 = new Thread(()->test.load(),"t1");
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(()->test.refresh(),"t2");
        t2.start();
    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end ;
        do{
            end = System.nanoTime();
        }while (start + interval >= end);
    }
}
