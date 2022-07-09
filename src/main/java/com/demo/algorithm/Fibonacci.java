package com.demo.algorithm;

/**
 * 斐波那契数列
 * 1,1,2,3,5,8,13,21,34,55...
 * f(n)=f(n-1)+f(n-2)
 * 时间复杂度和空间复杂度的优劣
 * O(1) > O(logn) > 0(n) > 0(nlogn) > 0(n^2)> 0(x^n)
 * 一段代码，尽量往O(nlogn)及以上来优化
 */
public class Fibonacci {
    /**
     * O(2^n) 时间和空间复杂度都很惊人，n>50基本都要运行3秒了！
     * @param n
     * @return
     */
    static int fab0(int n){
        if(n <= 2){
            return 1;
        }
        return fab0(n-1)+ fab0(n-2);
    }

    /**
     * 按理说，任何递归都可以使用一般的循环实现
     * O(n)
     * @param n
     * @return
     */
    static int fab1(int n){
        if(n<=2){
            return 1;
        }
        int a=1,b=1,c=0;
        for(int i=2;i<n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        return c;
    }

    /**
     * 思路1:可以将中间值缓存起来，这样就不用重新开辟空间，重新计算这些中间值了
     * 使用一个数组来缓存
     * O(n)
     * @param n
     * @return
     */
    static int data[];
    static int fab2(int n){
        if(n <= 2) {
            data[n] = 1;
            return 1;
        }
        if(data[n] > 0){
            return data[n];
        }
        int res = fab2(n-1)+fab2(n-2);
        data[n] = res;
        return res;
    }

    /**
     * 尾递归！！！ O(n)
     * 倒过来计算~~
     * 将结果当作参数往下传过去
     * @param n
     * @param pre
     * @param curr
     * @return
     */
    static int fab3(int n,int pre,int curr){
        if(n <= 2){
            return curr;
        }
        return fab3(n-1,curr,pre+curr);
    }



    public static void main(String[] args) {

//        long start0 = System.currentTimeMillis();
//        System.out.println(fab0(45));
//        System.out.println(System.currentTimeMillis() - start0);

//        long start1 = System.currentTimeMillis();
//        System.out.println(fab1(200));
//        System.out.println(System.currentTimeMillis() - start1);

//        long start2 = System.currentTimeMillis();
//        data = new int[201];
//        System.out.println(fab2(200));
//        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        data = new int[201];
        System.out.println(fab3(200,1,1));
        System.out.println(System.currentTimeMillis() - start3);

    }
}
