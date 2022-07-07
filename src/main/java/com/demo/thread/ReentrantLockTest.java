package com.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        lock.lock();
        System.out.println("111");
        lock.unlock();
    }
}
