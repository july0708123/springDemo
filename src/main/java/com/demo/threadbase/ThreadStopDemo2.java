package com.demo.threadbase;

/**
 * @author  Fox
 */
public class ThreadStopDemo2 implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);

            try {
                //处于休眠中的线程被中断，线程是可以感受到中断信号的，并且会抛出一个
                //InterruptedException 异常，同时清除中断信号，将中断标记位设置成 false
                //这样就会导致while条件里的中断条件判断是不中断的
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //重新设置线程中断状态为true
                //Thread.currentThread().interrupt();
            }
        }
        System.out.println("线程停止： stop thread");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadStopDemo2());
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
    }
}

