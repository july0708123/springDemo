package com.demo.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象头包含
 * 1、Mark word(标记字段) 8字节 其中有4位代表分代age，所以15代后移入老年代
 * 2、Klass pointer(类型指针) 4字节（指针压缩后）
 * 3、数组长度 当对象是数组时才会有
 * 4、填充对齐 （8字节的倍数才是最优效率）
 *
 * 启用指针压缩:-XX:+UseCompressedOops(默认开启)，禁止指针压缩:-XX:-UseCompressedOops
 */
public class TestJol {
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        ClassLayout layout2 = ClassLayout.parseInstance(new AA());
        System.out.println(layout2.toPrintable());
    }

}

class AA{
    String str;
    int num;
    AA(){}
}
