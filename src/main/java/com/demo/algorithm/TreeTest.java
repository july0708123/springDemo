package com.demo.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeTest {

    /**
     * 前序遍历 根左右
     * @param root
     */
    static void pre(TreeNode root){
        //根左右
        System.out.println(root.val);
        if(root.left != null){
            pre(root.left);
        }
        if(root.right != null){
            pre(root.right);
        }
    }

    /**
     * 中序遍历 左根右
     * 二叉树的中序遍历正好就是从小到大排序了
     * @param root
     */
    static void in(TreeNode root){
        if(root.left != null){
            in(root.left);
        }
        System.out.println(root.val);
        if(root.right != null){
            in(root.right);
        }
    }

    /**
     * 中序遍历
     * 不使用递归！要使用一个栈来存储节点
     * @param root
     */
    static Stack<TreeNode> stack = new Stack<>();
    static void inWhile(TreeNode root){
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.println(root.val);
            root = root.right;
        }
    }

    /**
     * 后序遍历 左右根
     * @param root
     */
    static void pos(TreeNode root){
        if(root.left != null){
            pos(root.left);
        }
        if(root.right != null){
            pos(root.right);
        }
        System.out.println(root.val);
    }

    /**
     * 层次遍历 (从上到下，从左到右，需要一个辅助队列)
     * @param root
     */
    static void level(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if(node.left!= null){
                queue.add(node.left);
            }
            if(node.right!= null){
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(15,null,null);
        TreeNode t2 = new TreeNode(7,null,null);
        TreeNode t3 = new TreeNode(20,t1,t2);
        TreeNode t4 = new TreeNode(9,null,null);
        TreeNode t5 = new TreeNode(3,t4,t3);
        //pre(t5);
        //level(t5);
        inWhile(t5);
        //in(t5);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
