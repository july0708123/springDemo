package com.demo.algorithm;

/**
 * 链表反转
 * 方法1，循环
 * 方法2，递归
 */
public class ListReverse {
    int code;
    String name;
    ListReverse next;

    public ListReverse(int code, String name, ListReverse next) {
        this.code = code;
        this.name = name;
        this.next = next;
    }

    /**
     * 循环，用prev,next节点来记录前后节点，e代表当前节点
     * @param head
     * @return
     */
    static ListReverse reverseTest1(ListReverse head){
        ListReverse e,prev,next;
        prev = null;
        e = head;
        while (e != null){
            next = e.next;
            e.next = prev;
            prev = e;
            e = next;
        }
        return prev;
    }

    /**
     * 递归：将大问题细化成两个节点的操作。
     *      要有结束的条件
     *
     * 核心操作：e.next.next = e; e.next=null
     * @param head
     * @return
     */
    static ListReverse node;
    static ListReverse reverseTest2(ListReverse head){
        if(head == null || head.next == null){
            if(head.next == null){
                //返回反转后的头结点（也就是反转前的最后一个节点）
                node = head;
            }
            return head;
        }
        reverseTest2(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }


    public static void main(String[] args) {
        ListReverse l5 = new ListReverse(5,"l5",null);
        ListReverse l4 = new ListReverse(4,"l4",l5);
        ListReverse l3 = new ListReverse(3,"l3",l4);
        ListReverse l2 = new ListReverse(2,"l2",l3);
        ListReverse l1 = new ListReverse(1,"l1",l2);

//        //循环方式
//        ListReverse res = reverseTest1(l1);
//        System.out.println(res);

        //递归方式
        ListReverse res2 = reverseTest2(l1);
        System.out.println(res2);
    }
}
