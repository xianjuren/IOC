package com.mnproxy;

import android.view.View;

import com.mnproxy.annotation.ContentView;
import com.mnproxy.annotation.EventBase;
import com.mnproxy.annotation.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by Jone on 4/17/21
 */
public class InjetUtil {

    private static final String TAG = "maniu";

    //注入方法 ioc
    public static void inject(Object context) {
        //setContentView 的逻辑
        injectLayout(context);
        injectView(context);
        injectClick(context);
    }


    /**
     * 点击事件
     * @param context
     */
    private static void injectClick(Object context) {
        Class<?> aClass = context.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            //不直接查找注解类型，可能有很多个，且解耦
//            OnClickEvent annotation1 = method.getAnnotation(OnClickEvent.class);
//            EventBase base = annotation1.getClass().getAnnotation(EventBase.class);
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //直接获取父类：EventBase，获取事件三要素，
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) continue;
                String listenerSetter = eventBase.listenerSetter();
                Class<?> listenerType = eventBase.listenerType();
                String callbackMethod = eventBase.callbackMethod();
                //1.先通过反射注解中value的方法，获取View的id ,再通过id获取View。上面的annotation代表 OnClickEvent注解
                try {
                    Method declaredMethod = annotationType.getDeclaredMethod("value");
                    int[] viewIds = (int[]) declaredMethod.invoke(annotation);//反射当前的annotation,获取id
                    for (int viewId : viewIds) {
                        //有多少id，需要findViewById多少个View
                        Method findViewById = aClass.getMethod("findViewById", int.class);
                        View view = (View) findViewById.invoke(context, viewId);
                        //事件的方法名是View的，不是Activity的，通过反射获取view对应方法，这里是多种类型，不能在直接写死
                        //view.getClass().getMethod("setOnClickListener")，传递方法名，参数类型
                        Method viewMethod = view.getClass().getMethod(listenerSetter, listenerType);
                        //view.setOnClick(动态代理：一个内部类)
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, new ListenerInvocationHandler(context, method));
                        viewMethod.invoke(view, proxy);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //2.
            }
        }
    }

    private static void injectView(Object context) {
        //反射
        Class<?> aClass = context.getClass();
        Field[] fields = aClass.getDeclaredFields();//获取所有的成员变量
        for (Field field : fields) {
            //寻找是有@ViewInject的注解成员变量
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            if (annotation == null) continue;
            int id = annotation.value();
            try {
                Method method = aClass.getMethod("findViewById", int.class);
                View view = (View) method.invoke(context, id);//findViewById返回一个View
                //此时的Button与这里的view还没有建立真实的联系
                field.setAccessible(true);//可访问
                field.set(context, view);//建立联系
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectLayout(Object context) {
        int layoutId = 0;
        Class<?> aClass = context.getClass();
        ContentView annotation = aClass.getAnnotation(ContentView.class);
        if (annotation == null) {
            return;
        }
        layoutId = annotation.value();
        try {
            Method method = context.getClass().getMethod("setContentView", int.class);
            method.invoke(context, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
