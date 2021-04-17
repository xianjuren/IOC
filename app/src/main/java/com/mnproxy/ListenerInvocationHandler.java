package com.mnproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Create by Jone on 4/17/21
 */

//处理器，处理器中拥有谁的对象，谁就是代理对象，代理对象不需要实现
public class ListenerInvocationHandler implements InvocationHandler {

    private Object context;
    private Method activityMethod;

    public ListenerInvocationHandler(Object context, Method activityMethod) {
        this.context = context;
        this.activityMethod = activityMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke ----------调用前");
        return activityMethod.invoke(context, args);
    }
}
