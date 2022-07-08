package com.demo.algorithm;

import java.util.Random;

/**
 * 约瑟夫问题：
 * 类似于丢手绢，放到谁后边谁淘汰
 * 思路：循环列表，删除某个随机节点
 */
public class JosephTest {
    //生成一个循环列表，返回头结点
    static NodeT generateRandomList(int num){
        NodeT head = null;
        NodeT prev = null;
        for(int i=0;i<num;i++){
            NodeT curr = new NodeT(i,"july"+i,null);
            if(i==0){
                head = curr;
                prev = curr;
            }else{
                prev.next = curr;
                prev = curr;
                if(i==num-1){
                    curr.next = head;
                }
            }
        }
        return head;
    }

    public static void main(String[] args) {
        NodeT head = generateRandomList(10);
        Random random = new Random();
        //玩5轮丢手绢
        for(int k=0;k<5;k++){
            int del = random.nextInt(10);
            NodeT curr = head;
            //为什么不用curr.code != del来判断，万一要删的是head，那么需要遍历找到指向head的那个节点
            int count = 0;
            while (curr.next.code != del && count < 10) {
                curr = curr.next;
                count++;
                //可能会去重复删同一个节点，重置del防止死循环
                if(count == 10){
                    del = random.nextInt(10);
                    count = 0;
                }
            }
            if(curr.next.code == head.code){
                head = curr.next.next;
            }
            NodeT d = curr.next;
            curr.next = curr.next.next;
            d.next = null;
            System.out.println(del+"号小伙伴"+d.name+"被淘汰了哦~~");
        }
        //打印出该循环列表
        NodeT c = head;
        System.out.println("留下的小伙伴有：");
        while (c.next.code != head.code){
            System.out.println(c.name);
            c=c.next;
        }
        System.out.println(c.name);
    }
}

class NodeT{
    int code;
    String name;
    NodeT next;

    public NodeT(int code, String name, NodeT next) {
        this.code = code;
        this.name = name;
        this.next = next;
    }

}
