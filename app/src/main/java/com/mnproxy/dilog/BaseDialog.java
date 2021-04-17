package com.mnproxy.dilog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.mnproxy.InjetUtil;

import androidx.annotation.NonNull;

/**
 * Create by Jone on 4/17/21
 */
public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjetUtil.inject(this);
    }
}
