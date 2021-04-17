package com.mnproxy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by Jone on 4/17/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //在参数上的成员变量
public @interface ViewInject {
    int value();
}
