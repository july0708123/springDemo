package com.demo.thread;

/**
 * 伪共享情况
 * 和cpu读取内存的空间局部性相关，缓存行（64byte）
 */
public class FairSharedCache {
    public static void main(String[] args) {
        Pointer pointer = new Pointer();
        int c = 100000000;
        Thread t1 = new Thread(()->{
            System.out.println("t1线程启动");
            long a1 = System.nanoTime();
            for(int i=0;i<c;i++){
                pointer.x++;
            }
            long a2 = System.nanoTime();
            System.out.println("t1线程结束,x="+pointer.x);
            System.out.println("t1:"+(a2-a1));

        },"t1");

        Thread t2 = new Thread(()->{
            System.out.println("t2线程启动");
            long a1 = System.nanoTime();
            for(int i=0;i<c;i++){
                pointer.y++;
            }
            long a2 = System.nanoTime();
            System.out.println("t2线程结束,y="+pointer.y);
            System.out.println("t2:"+(a2-a1));
        },"t2");

        t1.start();
        t2.start();
    }

}

class Pointer{
    volatile long x;
    long p1,p2,p3,p4,p5,p6,p7;
    volatile long y;
}
