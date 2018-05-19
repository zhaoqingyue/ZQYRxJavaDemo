package com.zqy.rxjavademo.rxjava.operator;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxjava.operator.merge.CombineLatestActivity;
import com.zqy.rxjavademo.rxjava.operator.merge.ConcatActivity;
import com.zqy.rxjavademo.rxjava.operator.merge.MergeActivity;
import com.zqy.rxjavademo.rxjava.operator.merge.StartWithActivity;
import com.zqy.rxjavademo.rxjava.operator.merge.ZipActivity;

import butterknife.OnClick;

public class OperatorMergeActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_operator_merge;
    }

    @Override
    protected String getTitleName() {
        return "合并操作符";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_merge, R.id.btn_concat, R.id.btn_startWith, R.id.btn_zip, R.id.btn_combineLatest})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_merge: {
                Intent intent = new Intent(this, MergeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_concat: {
                Intent intent = new Intent(this, ConcatActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_startWith: {
                Intent intent = new Intent(this, StartWithActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_zip: {
                Intent intent = new Intent(this, ZipActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_combineLatest: {
                Intent intent = new Intent(this, CombineLatestActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
