package com.demo.algorithm;

import java.util.*;

/**
 * 各种排序算法
 */
public class VariousSort {
    /**
     * 插入排序（从无序数组中拿一个数，插入到有序数组中，从后比较数据大小。类似于打扑克时的抓牌~）
     * 时间复杂度 O(n^2),空间复杂度 O(1) 没有另外开辟空间
     * 具有稳定性
     */
    static void insertSort(int[] data){
        for(int i=0;i<data.length;i++){
            for(int j=i-1;j>=0;j--){
                if(data[j] > data[j+1]){
                    //交换
                    data[j] = data[j]+data[j+1];
                    data[j+1]= data[j]-data[j+1];
                    data[j] = data[j]-data[j+1];
                }else{
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序（按length/2的缩小增量去一趟一趟比较，最终增量为1,每次比较都采用插入排序算法）
     * 时间复杂度 O(n^2) 但是比一般的O(n^2)快，空间复杂度 O(1)
     * 不稳定
     */
    static void shellSort(int[] data) {
        int j;
        for(int gap = data.length/2; gap > 0; gap /= 2){
            for(int i=gap;i<data.length;i++){
                int temp = data[i];
                for(j=i; j>=gap&&(temp<data[j-gap]);j-=gap){
                    data[j] = data[j-gap];
                }
                data[j] = temp;
            }
        }
    }

    /**
     * 归并排序（先/2进行分，直到都是一个数。然后边排序边并起来）
     * 时间复杂度 O(nlogn) 空间复杂度 O(n) 因为要开辟临时数组
     * 稳定
     */
    static void mergeSort(int[] data,int left,int right){
        if(left < right){
            int mid = (left + right)/2;
            mergeSort(data,left,mid);
            mergeSort(data,mid+1,right);
            //分完了，并起来
            merge(data,left,mid,right);
        }
    }
    static void merge(int[] data,int left,int mid,int right){
        int[] arr = new int[data.length]; //开辟一个临时数组存放排序后的数据。这个数组开辟也很耗时间，一般提到外边，以data.length只开辟一次
        int point1 = left;   //记录两个比较的指针
        int point2 = mid+1;
        int loc = left;      //临时数组当前位置
        while (point1 <= mid && point2 <= right){
            if(data[point1] <= data[point2]){
                arr[loc] = data[point1];
                loc++;
                point1++;
            }else{
                arr[loc] = data[point2];
                loc++;
                point2++;
            }
        }
        //当一边数据走完了，另一边的数据可能没走完，将另一边没走完的数据都依次放到临时数组中
        while(point1 <= mid){
            arr[loc++] = data[point1++];
        }
        while (point2 <= right){
            arr[loc++] = data[point2++];
        }
        //将临时数组中的数据放回到原来的数组中
        for(int i=left;i<=right;i++){
            data[i] = arr[i];
        }
    }

    /**
     * 选择排序（每次从无序数组中选出一个最小的排到有序数组的后边）
     * 时间复杂度 O(n^2)
     * 稳定
     */
    static void selectSort(int[] data){
        int temp = 0;
        for(int i=0;i<data.length;i++){
            int loc = i;
            for(int j=i+1;j<data.length;j++){
                if(data[j] < data[loc]){
                    loc = j;
                }
            }
            temp = data[i];
            data[i] = data[loc];
            data[loc] = temp;
        }
    }

    /**
     * 冒泡排序（大的数据往上冒）
     * 时间复杂度 O(n^2)
     * 稳定
     */
    static void bubbleSort(int[] data){
        for(int i=0;i<data.length-1;i++){
            for(int j=0;j<data.length-1-i;j++){
                if(data[j] > data[j+1]){
                    data[j] = data[j]+data[j+1];
                    data[j+1] = data[j]-data[j+1];
                    data[j] = data[j]-data[j+1];
                }
            }
        }
    }

    /**
     * 快速排序（找一个基准值，然后从右边找比它小的进行交换，再从左边找出比它大的进行交换，分为左右两边，然后左边右边的数组再重复之前的步骤。没有并的步骤）
     * 一般就是第一个数是基准，优化就是定一个合理的基准
     * 时间复杂度 O(nlogn)
     * 不稳定
     */
    static void quickSort(int[] data,int left,int right){
        int ll = left;
        int rr = right;
        int base = data[left];

        while (ll < rr){
            //先从右边往左，如果找到一个比base小的，交换后跳出
            while(ll < rr){
                if (data[rr] < base){
                    int temp = data[ll];
                    data[ll] = data[rr];
                    data[rr] = temp;
                    break;
                }
                rr--;
            }
            //再从左边往右走，如果找到一个比base大的，交换后跳出
            while (ll < rr){
                if(data[ll] > base){
                    int temp = data[ll];
                    data[ll] = data[rr];
                    data[rr] = temp;
                    break;
                }
                ll++;
            }
        }
        if(ll > left){
            quickSort(data,left,ll-1);
        }
        if(rr < right){
            quickSort(data,rr+1,right);
        }
    }


    /**
     * 堆排序（见堆树 HeapTree）
     */

    /**
     * 计数排序（有确定范围的整数，key-value存储，相同key的比较算法可选择）
     */

    /**
     * 桶排序（计数排序升级版，尽量增多桶的数量，使用映射函数尽量使数据均匀分散在不同的桶中，相同桶的比较算法可选择）
     */

    /**
     * 基数排序（非比较型算法。将数字按位数切割，放在不同桶里，从低位到高位排完就完成排序了）
     * 好神奇~
     * 假设给3位数的整数排序
     */
    static void radixSort(int[] data){

        //0-9的桶 ,数组+链表
        int dev = 1;
        for(int k=0;k<3;dev=dev*10,k++){
            List[] listArr = new ArrayList[10];
            for(int p=0;p<10;p++){
                listArr[p] = new ArrayList<Integer>();
            }
            for(int i=0;i<data.length;i++){
                listArr[(data[i]/dev)%10].add(data[i]);
            }
            int t = 0;
            for(int m=0;m<10;m++){
                int size = listArr[m].size();
                for(int n=0;n<size;n++){
                    data[t++] = Integer.valueOf(listArr[m].get(n).toString());
                }
            }
        }


    }

    public static void main(String[] args) {
        int[] data = {110,207,500,30,722,255,811,59,363,222,78,561,234,445,278,45,22,98,177,666};
        //insertSort(data);
        //shellSort(data);
        //mergeSort(data,0,data.length-1);
        //selectSort(data);
        //bubbleSort(data);
        quickSort(data,0,data.length-1);
        //radixSort(data);
        System.out.println(Arrays.toString(data));
    }
}
