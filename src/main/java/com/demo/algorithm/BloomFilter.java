package com.demo.algorithm;

import java.util.BitSet;

/**
 * 布隆过滤器
 * 理解了bitMap,也就好理解布隆过滤器了
 * 它是由一个很长的bit数组以及k个hash函数组成。
 * 多个hash（多hash尽可能均匀分散）,多位置置1
 * hash冲突
 * 重要！！！ 我判断不存在的肯定不存在，我判断存在的可能存在
 * 布隆过滤器不能进行删除（黑名单（想要从黑名单里去除，不能删，建一个白名单来判断））
 *
 * 降低误判率！可以通过公式来推导bit数组长度和hash函数个数
 */
public class BloomFilter {
    int size;
    BitSet bitSet ;  //bit数组，long[] 实现,long[]长度为 (size >> 6)+1
    public BloomFilter(int size){
        this.size = size;
        bitSet = new BitSet(size);
    }

    public void insert(String key){
        int hash1 = hash_1(key);
        int hash2 = hash_2(key);
        int hash3 = hash_3(key);
        bitSet.set(hash1);
        bitSet.set(hash2);
        bitSet.set(hash3);
    }

    public boolean find(String key){
        int hash1 = hash_1(key);
        int hash2 = hash_2(key);
        int hash3 = hash_3(key);
        if(bitSet.get(hash1) && bitSet.get(hash2) && bitSet.get(hash3)){
            return true;
        }
        return false;
    }

    public int hash_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash) % size;
    }

    public int hash_2(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash) % size;
    }

    public int hash_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash) % size;
    }

    public static void main(String[] args) {
        BloomFilter bf = new BloomFilter(Integer.MAX_VALUE);
//        System.out.println(bf.hash_1("1"));
//        System.out.println(bf.hash_2("1"));
//        System.out.println(bf.hash_3("1"));
        long start = System.currentTimeMillis();
        for(int i=1;i<=10000000;i++){
            bf.insert(""+i);
        }
        System.out.println("加入1千万个数所需："+(System.currentTimeMillis()-start)+"ms");
        System.out.println(bf.find("8888892"));
        //测试下误差率
        int count = 0;
        for(int i=10000001;i<=20000000;i++){
            if(bf.find(""+i)){
                count++;
            }
        }
        //误差率才0.0004%，很小啊
        System.out.println("总数1千万，count="+count+",误差率为："+(count/100000.0)+"%");
    }
}
