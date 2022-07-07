package com.demo.jvm;

import sun.misc.Launcher;

import java.net.URL;

public class TestJDKClassLoader {
    public static void main(String[] args) {
        //引导类加载器因为是c++写的，所以用java是获取不到的
        System.out.println(String.class.getClassLoader());
        //扩展类加载器
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        //应用程序类加载器
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());

        System.out.println();
        //Launcher.getClassLoader（）返回的类加载器AppClassLoader的实例
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassLoader.getParent();
        System.out.println("the bootstrapLoader : "+bootstrapLoader);
        System.out.println("the extClassLoader : "+extClassLoader);
        System.out.println("the appClassLoader : "+appClassLoader);


        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i=0;i<urls.length;i++){
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));

    }
}
