package com.demo.jvm;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 自定义类加载器
 * 1、自定义类加载器只需要继承java.lang.ClassLoader类。
 * 该类有两个核心方法，一个是loadClass(String,boolean),实现双亲委派机制。
 * 还有一个方法是findClass，默认实现是空方法。
 * 2、所以我们自定义类加载器主要是重写findClass方法
 *
 * 打破双亲委派机制
 * 1、重写类加载方法，实现自己的加载逻辑，不委派给双亲
 * 2、可以加个判断条件，使Object,String等这些核心类库，扩展类库还是使用双亲委派机制。
 *  2.1、因为沙箱安全机制，这些核心类库是不允许篡改的。、
 *
 */
public class MyClassLoaderTest{
    static class MyClassLoader extends ClassLoader{
        private String classPath;

        public MyClassLoader(String classPath){
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws IOException {
            name = name.replaceAll("\\.","/");
            FileInputStream fis = new FileInputStream(classPath + "/" +name+".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组
                return defineClass(name,data,0,data.length);
            }catch (IOException e){
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
//                    try {
//                        if (parent != null) {
//                            c = parent.loadClass(name, false);
//                        } else {
//                            c = findBootstrapClassOrNull(name);
//                        }
//                    } catch (ClassNotFoundException e) {
//                        // ClassNotFoundException thrown if class not found
//                        // from the non-null parent class loader
//                    }

                    //其他的核心类库还是使用双亲委派机制来加载
                    if(!name.startsWith("com.demo.controller")){
                        //两种方式均可
                        //c = super.loadClass(name,resolve);
                        c = this.getParent().loadClass(name);
                    }

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        c = findClass(name);

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        Class clazz = classLoader.loadClass("com.demo.jvm.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout",null);
        method.invoke(obj,null);
        System.out.println(clazz.getClassLoader());

        System.out.println();

        MyClassLoader classLoader1 = new MyClassLoader("D:/test");
        Class clazz1 = classLoader1.loadClass("com.demo.jvm.User1");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getDeclaredMethod("sout",null);
        method1.invoke(obj1,null);
        System.out.println(clazz1.getClassLoader());
    }
}
