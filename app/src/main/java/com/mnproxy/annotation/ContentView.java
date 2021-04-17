package com.mnproxy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by Jone on 4/17/21
 */
@Target(ElementType.TYPE)//在类上
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {
    int value();
}
