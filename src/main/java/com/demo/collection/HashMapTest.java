package com.demo.collection;


public class HashMapTest {
    public static void main(String[] args) {
//        int n = 10 - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        n = (n < 0) ? 1 :n+1;
//        System.out.println(n);
        int hashcode = "july".hashCode();
        System.out.println(hashcode);
        int code16 = hashcode >>> 16;
        System.out.println(code16);
        hashcode = hashcode^code16;
        System.out.println(hashcode);

        System.out.println(hash("july"));

        int a=1,b=2;
        if(a == 1)
            System.out.println("a");
        else if(b == 2){
            System.out.println("b");
        }else{
            System.out.println("else");
        }

    }
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
