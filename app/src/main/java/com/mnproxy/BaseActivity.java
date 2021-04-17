package com.mnproxy;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Create by Jone on 4/17/21
 */
public class BaseActivity extends Activity {

    //动态代理技术  IOC --》事件
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //IOC注入
        InjetUtil.inject(this);
    }
}
