package com.mnproxy.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by Jone on 4/17/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//注解的多态
@EventBase(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, callbackMethod = "onClick")
public @interface OnClickEvent {

    int[] value();
}
