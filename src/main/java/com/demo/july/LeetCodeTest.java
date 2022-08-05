package com.demo.july;

import org.springframework.util.StringUtils;
import sun.misc.Unsafe;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class LeetCodeTest {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int a2 = 11;
        int b2 = 22;
        int n = 6279;
        System.out.println(reverseBits(n));
    }

    public static int reverseBits(int i) {
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
                ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }
}


