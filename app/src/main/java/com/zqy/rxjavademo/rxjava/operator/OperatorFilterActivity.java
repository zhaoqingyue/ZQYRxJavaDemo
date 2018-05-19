package com.zqy.rxjavademo.rxjava.operator;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.DistinctActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.DistinctUntilChangedActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.ElementAtActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.FilterActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.FirstActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.LastActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.OfTypeActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.SkipActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.SkipLastActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.TakeActivity;
import com.zqy.rxjavademo.rxjava.operator.filter.TakeLastActivity;

import butterknife.OnClick;

public class OperatorFilterActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_operator_filter;
    }

    @Override
    protected String getTitleName() {
        return "过滤操作符";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_filter, R.id.btn_ofType, R.id.btn_take, R.id.btn_takeLast,
            R.id.btn_first, R.id.btn_last, R.id.btn_skip, R.id.btn_skipLast,
            R.id.btn_elementAt, R.id.btn_distinct, R.id.btn_distinctUntilChanged})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_filter: {
                Intent intent = new Intent(this, FilterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_ofType: {
                Intent intent = new Intent(this, OfTypeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_take: {
                Intent intent = new Intent(this, TakeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_takeLast: {
                Intent intent = new Intent(this, TakeLastActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_first: {
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_last: {
                Intent intent = new Intent(this, LastActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_skip: {
                Intent intent = new Intent(this, SkipActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_skipLast: {
                Intent intent = new Intent(this, SkipLastActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_elementAt: {
                Intent intent = new Intent(this, ElementAtActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_distinct: {
                Intent intent = new Intent(this, DistinctActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_distinctUntilChanged: {
                Intent intent = new Intent(this, DistinctUntilChangedActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
