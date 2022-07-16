package com.demo.algorithm;


import org.springframework.util.StringUtils;

import java.util.*;

public class HuffmanTree {
    HuffmanNode root;
    List<HuffmanNode> leafs = new ArrayList<>(); //收集叶子节点
    Map<String, Integer> wtMap = new HashMap<String, Integer>();  //字符和权重的map

    /**
     * 中序遍历
     *
     * @param root
     */
    void inOrder(HuffmanNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        System.out.println(root.chars + "：" + root.weight);
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    /**
     * 将字符串转为map，该map存储字符和次数（权重）
     *
     * @param str
     */
    void strToMap(String str) {
        if (!StringUtils.isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                Character c = str.charAt(i);
                int count = wtMap.get(c.toString()) == null ? 0 : wtMap.get(c.toString());
                wtMap.put(c.toString(), count + 1);
            }
        }
    }

    /**
     * 构建哈夫曼树
     */
    void createHuffmanTree() {
        String[] strArr = wtMap.keySet().toArray(new String[0]);
        PriorityQueue<HuffmanNode> pqueue = new PriorityQueue<>();   //优先队列，权重小的会插到前边
        for (String str : strArr) {
            HuffmanNode hnode = new HuffmanNode();
            hnode.chars = str;
            hnode.weight = wtMap.get(str);
            leafs.add(hnode);
            pqueue.add(hnode);
        }
        if(pqueue.size() > 1){
            while (!pqueue.isEmpty() && pqueue.size() > 1){
                //最小的两个点合并，再将合并后的点放入队列。如此循环，从小到大建树
                HuffmanNode lnode = pqueue.poll();
                HuffmanNode rnode = pqueue.poll();
                HuffmanNode node = new HuffmanNode(null,lnode.weight+rnode.weight,lnode,rnode,null);
                lnode.parent = node;
                rnode.parent = node;
                pqueue.add(node);
            }
            root = pqueue.poll();
        }else{
            root = pqueue.peek();
        }
    }

    /**
     * 创建密码本
     * @return
     */
    Map<String,String> secretBook(){
        Map<String,String> map = new HashMap<>();
        for(HuffmanNode leaf:leafs){
            String val = leaf.chars;
            String code = "";
            while (leaf.parent != null){
                if(leaf.parent.left == leaf){
                    code = "0" + code;
                }else{
                    code = "1" + code;
                }
                leaf = leaf.parent;
            }
            System.out.println(val+"："+code);
            map.put(val,code);
        }
        return map;
    }

    /**
     * 加密
     * @param info
     * @param book
     * @return
     */
    String encodeInfo(String info,Map<String,String> book){
        String sc = "";
        for(int i=0;i<info.length();i++){
            sc = sc + book.get(""+info.charAt(i));
        }
        return sc;
    }

    /**
     * 解密
     * @param secret
     * @param book
     * @return
     */
    String decodeSecret(String secret,Map<String,String> book){
        String str = "";
        Map<String,String> booknew = new HashMap<>(); //将密码本的key,value换个位置，方便查找
        Set<String> set = book.keySet();
        for(String k:set){
            booknew.put(book.get(k),k);
        }
        for(int i=0;i<secret.length();i++){
            for(int j=i+1;j<=secret.length();j++){
                String c = secret.substring(i,j);
                if(booknew.containsKey(c)){
                    str = str + booknew.get(c);
                    i = j-1;
                    break;
                }
            }
        }
        return str;
    }


    public static void main(String[] args) {
//        String string = "abbbacd";
        HuffmanTree tree = new HuffmanTree();
//        tree.strToMap(string);
        tree.wtMap.put("a", 3);
        tree.wtMap.put("b", 24);
        tree.wtMap.put("c", 6);
        tree.wtMap.put("d", 20);
        tree.wtMap.put("e", 34);
        tree.wtMap.put("f", 4);
        tree.wtMap.put("g", 12);
        tree.createHuffmanTree(); //建树
        Map<String,String> book = tree.secretBook(); //建立密码本

        String secret = tree.encodeInfo("ddgecaf",book);
        System.out.println("加密后："+secret);

        String info = tree.decodeSecret(secret,book);
        System.out.println("解密后："+info);

        System.out.println("=========");
    }

}

class HuffmanNode implements Comparable<HuffmanNode> {
    String chars;
    int weight;
    HuffmanNode left;
    HuffmanNode right;
    HuffmanNode parent;

    public HuffmanNode() {
    }

    public HuffmanNode(String chars, int weight, HuffmanNode left, HuffmanNode right, HuffmanNode parent) {
        this.chars = chars;
        this.weight = weight;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight-o.weight;
    }
}
