package com.zqy.rxjavademo.rxjava.operator;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxjava.operator.create.CreateActivity;
import com.zqy.rxjavademo.rxjava.operator.create.DeferActivity;
import com.zqy.rxjavademo.rxjava.operator.create.FromActivity;
import com.zqy.rxjavademo.rxjava.operator.create.IntervalActivity;
import com.zqy.rxjavademo.rxjava.operator.create.JustActivity;
import com.zqy.rxjavademo.rxjava.operator.create.RangeActivity;
import com.zqy.rxjavademo.rxjava.operator.create.TimerActivity;
import com.zqy.rxjavademo.rxjava.operator.merge.MergeActivity;

import butterknife.OnClick;

public class OperatorCreateActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_operator_create;
    }

    @Override
    protected String getTitleName() {
        return "创建操作符";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_create, R.id.btn_just, R.id.btn_from, R.id.btn_timer,
            R.id.btn_interval, R.id.btn_range, R.id.btn_defer})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create: {
                Intent intent = new Intent(this, CreateActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_just: {
                Intent intent = new Intent(this, JustActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_from: {
                Intent intent = new Intent(this, FromActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_timer: {
                Intent intent = new Intent(this, TimerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_interval: {
                Intent intent = new Intent(this, IntervalActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_range: {
                Intent intent = new Intent(this, RangeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_defer: {
                Intent intent = new Intent(this, DeferActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_merge: {
                Intent intent = new Intent(this, MergeActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
