package com.demo.jvm;

/**
 * 添加运行JVM参数打印gc情况： -XX:+PrintGCDetails
 *
 * -XX:PretenureSizeThreshold=1000000 -XX:+UseSerialGC    大对象直接放入老年代
 * 为了避免为大对象分配内存时的复制操作而降低效率
 */
public class TestGc2 {
    public static void main(String[] args) {
        byte[] allocation1/*, allocation2, allocation3, allocation4, allocation5, allocation6*/;
        allocation1 = new byte[1024*1000*60]; //大概60M，能把eden区放满
//        allocation2 = new byte[1024*1000*8]; //再放8M，60M的大对象直接移到老年代，因为survivor区放不下
//        allocation3 = new byte[1024*1000*1]; //后续再放这些1M，会放入eden区
//        allocation4 = new byte[1024*1000*1];
//        allocation5 = new byte[1024*1000*1];
//        allocation6 = new byte[1024*1000*1];
    }
}
