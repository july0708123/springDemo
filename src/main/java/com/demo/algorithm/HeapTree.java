package com.demo.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 堆树(下标为i的左子节点是2i+1,右子节点是2i+2)
 * 知道子节点的下标j,那么父节点下标为 （j+1）/2-1
 * 完全二叉树
 * 数组实现
 * 堆排序，求解top n的题目
 */
public class HeapTree {

    /**
     * 堆排序
     * 先将数组做成一个大顶堆，再交换堆顶做堆化。
     * @param data
     */
    static void heapSort(int[] data){
        //先将数组做成大顶堆
        heapData(data);
        //再排序
        //将堆顶的数和最后一个数交换，然后以堆顶到len-1做堆化。然后再将堆顶和倒数第二个数做交换，再以堆顶到len-2做堆化
        //以此类推，直到i=0
        int len = data.length;
        for(int i=len-1;i>0;i--){
            //交换
            data[0] = data[0] + data[i];
            data[i] = data[0] - data[i];
            data[0] = data[0] - data[i];
            //堆化
            maxHeaped(data,0,i);
        }
    }

    /**
     * 堆化
     * 从start开始往下交换，直到不需要交换
     * @param data
     * @param start
     * @param end
     */
    static void maxHeaped(int[] data,int start,int end){
        int parent = start;
        int son = parent*2+1;
        int temp = son;
        while(son < end){
            if(son+1 < end && data[son] < data[son+1]) temp = son+1;
            if(data[parent] < data[temp]){
                data[parent] = data[parent] + data[temp];
                data[temp] = data[parent] - data[temp];
                data[parent] = data[parent] - data[temp];
            }
            parent = temp;
            son = parent*2 + 1;
            temp = son;
        }
    }

    /**
     * 将数组做成大顶堆
     * @param data
     */
    static void heapData(int[] data){
        //先将数组从最后一个叶子节点(len/2-1)，开始堆化(比如，做成一个大顶堆)
        for(int i = data.length/2-1;i>=0;i--){
            maxHeaped(data,i,data.length);
        }
    }

    /**
     * 新增
     * 方法一：将数字新增到堆顶
     * 方法二：将数字新增到最后
     * 无论哪种方法，都要做堆化，不能破坏堆树的特性：父节点大于等于或者小于等于子节点
     * @param data
     * @param num
     */
    static int[] insertHeap(int[] data,int num){
        //假设这个data是个大顶堆
        //使用方法2：新增到最后
        data = Arrays.copyOf(data,data.length+1);
        data[data.length-1] = num;
        //从新增的节点往上做交换，直到交换结束
        int son = data.length-1;
        while(son > 0){
            int parent = (son+1)/2-1;
            if(data[parent] < data[son]){
                //交换
                int temp = data[parent];
                data[parent] = data[son];
                data[son] = temp;
                //往上走
                son = parent;
            }else{
                //不用交换，跳出循环
                break;
            }
        }
        return data;
    }

    /**
     * 删除某一个节点
     * 将最后一个节点放到该位置，然后从该节点往下做堆化，直到交换结束
     * @param data
     * @param idx
     * @return
     */
    static int[] removeHeap(int[] data,int idx){
        int len = data.length;
        data[idx] = data[len-1];
        data = Arrays.copyOf(data,len-1);
        int parent = idx;
        int son = parent*2+1;
        int temp = son;
        while (son < len-1){
            if(son+1 < len-1 && data[son+1] > data[son]) temp = son+1;
            if(data[parent] < data[temp]){
                //交换
                data[parent] = data[parent] + data[temp];
                data[temp] = data[parent] - data[temp];
                data[parent] = data[parent] - data[temp];
                parent = temp;
                son = parent*2+1;
                temp = son;
            }else{
                //这是个大顶堆，已经堆化好了，有一次不需要交换，往后都不需要交换，可以直接跳出
                break;
            }
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
//        int data[] = {8, 4, 20, 7, 3, 1, 25, 14, 17};
//        heapSort(data);
//        System.out.println(Arrays.toString(data));

        // [25, 17, 20, 14, 3, 1, 8, 4, 7]
//        heapData(data);
//        System.out.println(Arrays.toString(data));

        //[25, 22, 20, 14, 17, 1, 8, 4, 7, 3]
//        data = insertHeap(data,22);
//        System.out.println(Arrays.toString(data));

        // [25, 14, 20, 7, 3, 1, 8, 4]
//        data = removeHeap(data,1);
//        System.out.println(Arrays.toString(data));

        //先随机出大概1亿个整数存到文本中
//        int len = 100000000;
//        Random random = new Random();
//        File file =new File("D:\\bignum.txt");
//        Writer out =new FileWriter(file);
//        for (int i =0;i<len;i++){
//            out.write(random.nextInt(len) + "\r\n");
//        }
//        out.close();
        /**
         * 作业：1亿个数据取top10
         * 思路：做一个size=10的小顶堆，每次读一个数，去和堆顶数字比较，比堆顶大就替换，然后做一次堆化。
         * 时间复杂度 O(nlog10) => O(n)  空间复杂度 O(1)
         * 除了读取文件的时间，堆化的时间忽略不计啊。。。妙啊。
         * 而且空间只是int[10]
         */
        //读取文本数据，和小顶堆的堆顶做比较
        long start = System.currentTimeMillis();
        String fileName = "D:\\bignum.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader reader = new BufferedReader(isr);
        int[] arr = new int[10];
        int s=0;
        String str = null;
        int cur = 0;
        while ((str = reader.readLine()) != null){
            cur = Integer.valueOf(str);
            if(cur > arr[0]){
                arr[0] = cur;
                int parent = 0;
                int son = parent*2+1;
                while (son < 10){
                    if(son + 1 < 10 && arr[son] > arr[son+1]) son = son + 1;
                    if(arr[parent] > arr[son]){
                        //小顶堆，最小的数在堆顶
                        arr[parent] = arr[parent] + arr[son];
                        arr[son] = arr[parent] - arr[son];
                        arr[parent] = arr[parent] - arr[son];
                        parent = son;
                        son = parent*2+1;
                    }else{
                        break;
                    }
                }
            }
        }
        System.out.println("文件读取完毕，top10也取出来啦，用时："+(System.currentTimeMillis()-start)+"ms");

        //将选出来的top10小顶堆排个序~
        for(int i=9;i>0;i--){
            //交换
            arr[0] = arr[0] + arr[i];
            arr[i] = arr[0] - arr[i];
            arr[0] = arr[0] - arr[i];
            //从0到i-1做堆化
            int parent = 0;
            int son = parent*2+1;
            while (son < i){
                if(son+1 < i && arr[son] > arr[son+1]) son = son+1;
                if(arr[parent] > arr[son]){
                    arr[parent] = arr[parent] + arr[son];
                    arr[son] = arr[parent] - arr[son];
                    arr[parent] = arr[parent] - arr[son];
                    parent = son;
                    son = parent*2+1;
                }else{
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
