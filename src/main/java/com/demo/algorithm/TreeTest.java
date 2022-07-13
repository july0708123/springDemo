package com.demo.algorithm;

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

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1,null,null);
        TreeNode t2 = new TreeNode(3,null,null);
        TreeNode t3 = new TreeNode(2,t1,t2);
        TreeNode t4 = new TreeNode(5,null,null);
        TreeNode t5 = new TreeNode(4,t3,t4);
        in(t5);
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
