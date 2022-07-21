package com.demo.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * 就是bit类型的数组，只能放0和1，1代表该下标对应的数存在
 * 使用int[] 模拟bit类型数组
 * 先找到属于那个下标 n/32  i
 * 再看属于哪个bit位，n%32  p 取余操作时间不如位运算，n&31可以代替取余操作
 * 使用或运算将该bit位置1，data[i] = data[i]|(1<<p)
 */
public class BitMap {
    int[] data;

    /**
     * 根据最大值来分配数组大小
     * @param max
     */
    public void initData(int max) throws Exception {
        //计算数组长度：最大值/32+1 (int数组时4个字节，就是32位。如果是是long类型的就是除以64)
        data = new int[max/32+1];
        //读取文件
        long start = System.currentTimeMillis();
        String fileName = "D:\\bignum.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader reader = new BufferedReader(isr);
        String str = null;
        while ((str = reader.readLine()) != null){
            int n = Integer.valueOf(str);
            data[n/32] |= (1<<(n&31));
        }
    }

    /**
     * 判断n是否存在
     * @param n
     */
    public boolean findNum(int n){
        //同理先找到数组下标对应的数和在该数上的二进制位位置
        int c = data[n/32];
        int p = n&31;
        if(((c >> p) & 1) == 1){
            return true;
        }
        return false;
    }

    /**
     * 删除某个数
     * bitmap对于重复的数没有办法判断
     * 所以删除的话，就是删除这个数
     * @param n
     */
    public void removeNum(int n){
        //同理先找到数组下标对应的数和在该数上的二进制位位置
        int c = data[n/32];
        int p = n&31;
        System.out.println("需置0的二进制位："+p);
        System.out.println("删除前："+Integer.toBinaryString(c));
        //对应位置置0,还有取反操作这个神技呢~
        c &= ~(1<<p);
        System.out.println("删除后："+Integer.toBinaryString(c));
        data[n/32] = c;
    }

    /**
     * 之前堆树的那个文件(1亿个数据，最大值为1亿)
     * 使用那个文件来做查询
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //top10:[99999998, 99999998, 99999997, 99999997, 99999990, 99999990, 99999988, 99999988, 99999988, 99999987]
        BitMap bm = new BitMap();
        long start = System.currentTimeMillis();
        bm.initData(100000000);
        System.out.println("初始化数组时间："+(System.currentTimeMillis()-start)+"ms");
        System.out.println(bm.findNum(99999987));   //true
        System.out.println(bm.findNum(99999990));   //true
        System.out.println(bm.findNum(99999991));   //false
        System.out.println(bm.findNum(3));          //true

        //删除几个数
        bm.removeNum(99999990);
        bm.removeNum(3);

        System.out.println(bm.findNum(99999987));   //true
        System.out.println(bm.findNum(99999990));   //false
        System.out.println(bm.findNum(99999991));   //false
        System.out.println(bm.findNum(3));          //false

    }
}
