package com.mnproxy.dilog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mnproxy.R;
import com.mnproxy.annotation.ContentView;
import com.mnproxy.annotation.OnClickEvent;
import com.mnproxy.annotation.ViewInject;

import androidx.annotation.NonNull;

/**
 * Create by Jone on 4/17/21
 */

@ContentView(R.layout.new_dialog)
public class NewDialog extends BaseDialog {
    @ViewInject(R.id.btn_dialog)
    Button mButton;

    public NewDialog(@NonNull Context context) {
        super(context);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Toast.makeText(getContext(), "dialog " + mButton, Toast.LENGTH_SHORT).show();
//    }

    @OnClickEvent(R.id.btn_dialog)
    public void onClickView(View view) {
        Toast.makeText(getContext(), "dialog 被点击了", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
