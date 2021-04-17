package com.mnproxy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by Jone on 4/17/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE) //写在注解上的注解，实现注解的多态
public @interface EventBase {
    //定义事件的三要素：1.方法名：setOnClickListener，setOnLongClickListener；
    String listenerSetter();

    // 2.内部类的Class类型不一致,事件的监听类型；
    Class<?> listenerType();

    // 3.回调方法名不一样
    String callbackMethod();
}
