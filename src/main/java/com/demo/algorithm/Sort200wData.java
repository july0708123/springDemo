package com.demo.algorithm;

import java.io.*;

/**
 * 两百万个成绩数据排序
 * 不同的算法，比较使用的时间多少
 * 快排和归并均需要200ms左右，countSort只需要10ms,快了20多倍！！
 */
public class Sort200wData {
    private static double[] temp ;
    public static void main(String[] args) throws Exception {
        String fileName0 = "";
        String fileName = "E:\\200w.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader reader = new BufferedReader(isr);
        double[] data = new double[2100001];
        int i=0;
        String str = null;
        while ((str = reader.readLine()) != null){
            data[i++] = Double.valueOf(str);
        }
        System.out.println("数据读取完毕~~~，size为："+i);
//
//        long start = System.currentTimeMillis();
//        qSort(data,0,i-1);
//        long end = System.currentTimeMillis();
//        System.out.println("快速排序的时间为："+(end-start)+" ms");
//        fileName0 = "E:\\200w-qsort.txt";

//        long start = System.currentTimeMillis();
//        temp = new double[i];  //开辟临时数组，只开辟一次，节约时间和内存
//        mergeSort(data,0,i-1);
//        long end = System.currentTimeMillis();
//        System.out.println("归并排序的时间为："+(end-start)+" ms");
//        fileName0 = "E:\\200w-merge.txt";

        //数组下标代表分数，存的值代表该分数出现次数
        long start = System.currentTimeMillis();
        countSort(data,99999);
        long end = System.currentTimeMillis();
        System.out.println("不使用排序的时间为："+(end-start)+" ms");
        fileName0 = "E:\\200w-count.txt";

        File file =new File(fileName0);
        Writer out =new FileWriter(file);
        for(i = 0 ; i< data.length ; i++) {
            out.write(String.valueOf(data[i]) + "\r\n");
        }
        out.close();
    }

    static void qSort(double[] data,int left,int right){
        int ll = left;
        int rr = right;
        double base = data[left];
        while (ll < rr){
            while (ll < rr && data[rr] >= base) rr--;
            if(ll < rr){
                double temp = data[ll];
                data[ll] = data[rr];
                data[rr] = temp;
            }
            while (ll < rr && data[ll] <= base) ll++;
            if(ll < rr){
                double temp = data[ll];
                data[ll] = data[rr];
                data[rr] = temp;
            }
        }
        if (left < ll){
            qSort(data,left,ll-1);
        }
        if (rr < right){
            qSort(data,rr+1,right);
        }
    }

    static void mergeSort(double[] data,int left,int right){
        if(left < right){
            int mid = (left+right)/2;
            mergeSort(data,left,mid);
            mergeSort(data,mid+1,right);
            merge(data,left,mid,right);
        }
    }
    static void merge(double[] data,int left,int mid,int right){
        int point1 = left;
        int point2 = mid+1;
        int loc = left;
        while (point1 <= mid && point2 <= right){
            if(data[point1] <= data[point2]){
                temp[loc++] = data[point1++];
            }else{
                temp[loc++] = data[point2++];
            }
        }
        while (point1 <= mid){
            temp[loc++] = data[point1++];
        }
        while (point2 <= right){
            temp[loc++] = data[point2++];
        }
        for(int i=left;i<=right;i++){
            data[i] = temp[i];
        }
    }

    static void countSort(double[] data,int max){
        int[] arr = new int[max];
        for(int i=0;i<data.length;i++){
            arr[(int)(data[i]*100)]++;
        }
        int t=0;
        for(int j=0;j<arr.length;j++){
            if(arr[j] > 0){
                for(int k=0;k<arr[j];k++){
                    data[t++] = j/100.0;
                }
            }
        }
    }

}
