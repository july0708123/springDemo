package com.demo.jvm;

public class Math {
    public static final int INiTDATA = 666;
    public static User user = new User();

    public int compute(){
        int a = 1;
        int b = 2;
        int c = (a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        while(true){
            math.compute();
        }
    }
}
