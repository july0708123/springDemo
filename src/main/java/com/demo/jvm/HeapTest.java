package com.demo.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {
    byte[] bytes = new byte[1024*100]; //100kb
    public static void main(String[] args) throws InterruptedException {
        List<HeapTest> list = new ArrayList<>();
        while (true){
            list.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
