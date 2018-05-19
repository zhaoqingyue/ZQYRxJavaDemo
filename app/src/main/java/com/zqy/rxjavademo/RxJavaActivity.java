package com.zqy.rxjavademo;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxjava.RxJava0Activity;
import com.zqy.rxjavademo.rxjava.operator.OperatorActivity;

import butterknife.OnClick;

public class RxJavaActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected String getTitleName() {
        return "RxJava";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn0, R.id.btn1})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0: {
                Intent intent = new Intent(this, RxJava0Activity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn1: {
                Intent intent = new Intent(this, OperatorActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
