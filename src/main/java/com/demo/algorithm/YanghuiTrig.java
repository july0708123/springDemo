package com.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角
 * 尾递归
 */
public class YanghuiTrig {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        sub.add(1);
        list.add(sub);
        generate(list,5);
        System.out.println(Arrays.toString(list.toArray()));
    }

    static List<List<Integer>> generate(List<List<Integer>> list,int numRows){
        if(numRows == 1){
            return list;
        }else{
            List<Integer> lastList = list.get(list.size()-1);
            List<Integer> newList = new ArrayList<>();
            for(int i = 0;i<=lastList.size();i++){
                if(i == 0 || i == lastList.size()){
                    newList.add(1);
                }else{
                    newList.add(lastList.get(i-1)+lastList.get(i));
                }
            }
            list.add(newList);
            numRows--;
            return generate(list,numRows);
        }

    }
}
