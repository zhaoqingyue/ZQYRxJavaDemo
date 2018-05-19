package com.zqy.rxjavademo.rxjava.operator;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.OnClick;

public class OperatorActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_operator;
    }

    @Override
    protected String getTitleName() {
        return "RxJava操作符";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_create_operator, R.id.btn_merge_operator, R.id.btn_filter_operator,
            R.id.btn_map_operator})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_operator: {
                Intent intent = new Intent(this, OperatorCreateActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_merge_operator: {
                Intent intent = new Intent(this, OperatorMergeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_filter_operator: {
                Intent intent = new Intent(this, OperatorFilterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_map_operator: {
                Intent intent = new Intent(this, OperatorMapActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
