package com.demo.thread;

/**
 * cpu 空间局部性
 * 按缓存行读取（64byte）,减少和内存的交互
 */
public class CpuCacheTest {
    public static void main(String[] args) {
        int col_big = 1024*1024;
        int col_small = 6;
        long[][] arr = new long[col_big][col_small];
        int runs = 100;
        for(int i=0;i<col_big;i++){
            for (int j=0;j<col_small;j++){
                arr[i][j] = 1l;
            }
        }

        long c1 = 0l;
        long c2 = 0l;

        long t1 = System.currentTimeMillis();
        for(int k=0;k<runs;k++){
            for(int i=0;i<1024*1024;i++){
                for(int j=0;j<6;j++){
                    c1 = c1 + arr[i][j];
                }
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("c1="+c1);
        System.out.println("for1="+(t2-t1));

        for(int k=0;k<runs;k++){
            for(int m=0;m<6;m++){
                for(int n=0;n<1024*1024;n++){
                    c2 = c2 + arr[n][m];
                }
            }
        }

        long t3 = System.currentTimeMillis();
        System.out.println("c2="+c2);
        System.out.println("for2="+(t3-t2));
    }
}
