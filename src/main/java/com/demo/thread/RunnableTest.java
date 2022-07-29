package com.demo.thread;


public class RunnableTest implements Runnable{
    int count =0;
    @Override
    public void run() {
//        synchronized (this){
            for(int i=0;i<10;i++){
                try {
                    System.out.println(Thread.currentThread().getName()+"==========");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunnableTest test = new RunnableTest();
        Thread t1 = new Thread(test,"t1");
        Thread t2 = new Thread(test,"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(test.count);
    }
}
