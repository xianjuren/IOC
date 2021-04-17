package com.mnproxy;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.mnproxy.annotation.ContentView;
import com.mnproxy.annotation.OnClickEvent;
import com.mnproxy.annotation.OnLongClickEvent;
import com.mnproxy.annotation.ViewInject;
import com.mnproxy.dilog.NewDialog;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.btn_try)
    Button mButton;

    @OnLongClickEvent(R.id.btn_try)
    public boolean clickEvent(View view) {//随意命名的方法的参数及返回值必须与实际控件的点击方法保持一致，不然会报错的
        Toast.makeText(this, "长按调用了", Toast.LENGTH_SHORT).show();
        return true;
    }

    //动态代理,可以多个view绑定到一个方法上，id可以是多个
    @OnClickEvent({R.id.btn_try_2, R.id.btn_try})
    public void click(View view) {//随意命名的方法的参数及返回值必须与实际控件的点击方法保持一致，不然会报错的
        switch (view.getId()) {
            case R.id.btn_try_2:
                Toast.makeText(this, "1调用了", Toast.LENGTH_SHORT).show();
                break;
            default:
                NewDialog newDialog = new NewDialog(this);
                newDialog.show();
                break;
        }

    }


}