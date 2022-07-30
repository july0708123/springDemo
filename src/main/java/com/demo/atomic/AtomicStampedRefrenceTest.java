package com.demo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class AtomicStampedRefrenceTest {
    public static void main(String[] args) {
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1,1);
        new Thread(()->{
            int[] stampHoder = new int[1];
            int value = (int) atomicStampedReference.get(stampHoder);
            int stamp =stampHoder[0];
            log.debug("Thread1 read value: " + value + ", stamp: " + stamp);
            //阻塞1秒
            LockSupport.parkNanos(1000000000L);
            //Thread1通过CAS修改value值为3
            if(atomicStampedReference.compareAndSet(value,3,stamp,stamp+1)){
                log.debug("Thread1 update from " + value + " to 3");
            }else{
                System.out.println("自己的版本="+stamp);
                System.out.println("内存中的版本="+atomicStampedReference.getStamp());
                log.debug("Thread1 update fail!");
            }
        },"Thread1").start();

        new Thread(()->{
            int[] stampHolder = new int[1];
         int value = (int)atomicStampedReference.get(stampHolder);
         int stamp = stampHolder[0];
         log.debug("Thread2 read value: " + value+ ", stamp: " + stamp);
         // Thread2通过CAS修改value值为2
         if (atomicStampedReference.compareAndSet(value, 2,stamp,stamp+1)) {
             log.debug("Thread2 update from " + value + " to 2");
             // do something
             value = (int) atomicStampedReference.get(stampHolder);
             stamp = stampHolder[0];
             log.debug("Thread2 read value: " + value+ ", stamp: " + stamp);
             // Thread2通过CAS修改value值为1
             if (atomicStampedReference.compareAndSet(value, 1,stamp,stamp+1)) {
                 log.debug("Thread2 update from " + value + " to 1");
                 }
             }
         },"Thread2").start();

    }
}
