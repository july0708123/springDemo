package com.demo.jvm;

/**
 * class常量池这些静态信息在运行时载入内存就时运行时常量池，符号引用有了内存地址就转变为动态链接
 * 字符串常量池，jdk1.7及以后在堆中
 * 八大基本类的包装类和对象池
 */
public class ConstantPoolTest {
    public static void main(String[] args) {
        String s = "july";
        String s1 = "july";
        String s2 = "ju"+"ly";
        String s3 = "ju"+new String("ly");
        String s4 = new String("july");
        String s5 = s4.intern();
        System.out.println(s == s1);     //true 都指向常量池的july
        System.out.println(s == s2);     //true 编译时s2就优化为july (由多个字符串常量连接而成)
        System.out.println(s == s3);     //false 因为有new 编译时无法优化，s3指向新建的内存对象july
        System.out.println(s == s4);     //false 新建一个内存对象
        System.out.println(s == s5);     //true 字符串常量池中存在july,则返回常量池的对象引用
        System.out.println(s3 == s4);    //false 明显的两个堆里的对象
        System.out.println(s3 == s5);    //false s5是指向常量池的对象
        System.out.println(s4 == s5);    //false s5是指向常量池的对象

        System.out.println();
        String s7 = new String("zhong");
        String s8 = s7.intern();
        String s88 = "zhong";
        System.out.println(s7 == s8);     //false s8是指向常量池的对象
        System.out.println(s8 == s88);    //true s88同样是指向常量池的对象


        System.out.println();
        String s9 = "lin" + new String("fen"); //因为没有出现linfen这个字面量，所以常量池中没有linfen，只有内存对象中有linfen
        String s10 = s9.intern();
        System.out.println(s9 == s10);   //true 常量池中没有linfen这个字段，linfen这个对象在非常量池内存中

        System.out.println();
        String s11 = "ja" + new String("va"); //虽然这里没有出现java字面量，但是java应该在编译期中会出现的，这是个java程序呀，是个关键字吧
        String s12 = s11.intern();
        System.out.println(s11 == s12);  //false

        System.out.println();
        Integer a = 127;
        Integer a1 = 127;
        System.out.println(a == a1); //true
        Integer b = 128;
        Integer b1 = 128;
        System.out.println(b == b1); //false  -128~127之间才使用对象池
        Double c = 1D;
        Double c1 = 1D;
        System.out.println(c == c1); //Float和Double没实现对象池技术

    }
}
