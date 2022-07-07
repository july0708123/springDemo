package com.demo.jvm;

/**
 * 栈上分配依赖于逃逸分析和标量替换
 * 代码调用了1亿次alloc()，如果是分配到堆上，大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
 * 使用如下参数不会发生GC
 * ‐Xmx15m ‐Xms15m ‐XX:+DoEscapeAnalysis ‐XX:+PrintGC ‐XX:+EliminateAllocations
 * 使用如下参数都会发生大量GC
 * ‐Xmx15m ‐Xms15m ‐XX:‐DoEscapeAnalysis ‐XX:+PrintGC ‐XX:+EliminateAllocations
 * -Xmx15m ‐Xms15m ‐XX:+DoEscapeAnalysis ‐XX:+PrintGC ‐XX:‐EliminateAllocations
 */
public class AllotOnStack {
    private static void alloc(){
        User user = new User();
        user.setId(1);
        user.setName("july");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("所用时间："+(end-start));
    }
}
