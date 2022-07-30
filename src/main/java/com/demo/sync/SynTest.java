package com.demo.sync;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class SynTest {
    public static void main(String[] args) throws InterruptedException {
        //无锁
        Object obj01 = new Object();
        log.debug("4秒之前创建。。。\n"+ClassLayout.parseInstance(obj01).toPrintable());
        Thread.sleep(5000);
        //匿名偏向锁
//        Object obj1 = new Object();
//        log.debug("4秒之后创建。。。\n"+ClassLayout.parseInstance(obj1).toPrintable());
//        obj1.hashCode();
//        //变回无锁状态，因为偏向锁没法存储hash值
//        log.debug("调用了hashCode方法后。。。\n"+ClassLayout.parseInstance(obj1).toPrintable());

        //
        Object obj = new Object();
        log.debug("4秒之后创建。。。\n"+ClassLayout.parseInstance(obj).toPrintable());

    }


}
