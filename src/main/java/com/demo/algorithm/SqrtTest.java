package com.demo.algorithm;

/**
 * 求一个数的平方根
 * 方法一：二分查找
 * 方法二：牛顿迭代法 Xn+1 = (Xn+C/Xn)/2  2是导数
 *
 */
public class SqrtTest {
    /**
     * 使用二分查找方式
     * @param c
     * @return
     */
    static double sqrtBinary(double c){
        if(c < 0){
            return Double.NaN;
        }
        double left = 0;
        double right = c;
        double mid = (left+right)/2;
        int count=1;
        while (compare(mid,right)){
            count++;
            if(mid*mid == c){
                break;
            } else if(mid*mid > c){
                right = mid;
            }else{
                left = mid;
            }
            mid = (left+right)/2;
        }
        System.out.println("二分查找次数："+count);
        return mid;
    }

    /**
     * 使用牛顿迭代法
     * @param c
     * @return
     */
    static double sqrtNewton(double c){
        if(c < 0){
            return Double.NaN;
        }
        double x=c;
        double y=(x+(c/x))/2;
        int count = 1;
        while (compare(x,y)){
            x = y;
            y=(x+(c/x))/2;
            count++;
        }
        System.out.println("牛顿迭代法次数："+count);
        return y;
    }

    /**
     * 控制误差，也就是结束条件
     * @param a
     * @param b
     * @return
     */
    static boolean compare(double a,double b){
        if(Math.abs(a-b) > 0.0000000001){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sqrtNewton(9));
        System.out.println(sqrtBinary(9));
    }
}
