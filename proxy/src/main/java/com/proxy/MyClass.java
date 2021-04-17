package com.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//import sun.misc.ProxyGenerator;

public class MyClass {

    public static void main(String[] args) {
        Hello hello = new Hello();
        HelloInterface helloInterface = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(), new ProxyHandler(hello));
        helloInterface.sayHello();
    }


    public static void main2(String[] args) throws IOException {
//        //通过class字节码生成的byte数组是直接存在内存中的，动态代理Proxy生成的字节码是直接加载到内存中，而不需要存在磁盘
//        byte[] bytes = ProxyGenerator.generateProxyClass("JoneHelloImpl", new Class[]{HelloInterface.class});
//        //把字节码写入 在指定目录的JoneHelloImpl.class文件
//        File file = new File("/Users/renxianju/Android_Projects/mn_kn_record/MnProxy/proxy/src/main/java/com/proxy/JoneHelloImpl.class");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);
//        outputStream.flush();
//        outputStream.close();
    }

    static class ProxyHandler implements InvocationHandler {
        Object mObject;

        public ProxyHandler(Object object) {
            this.mObject = object;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            System.out.println("jone----------s1");
            method.invoke(mObject, objects);
           System.out.println("jone----------s2");
            return null;
        }
    }
}