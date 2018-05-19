package com.zqy.rxjavademo.rxjava.operator;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxjava.operator.map.BufferActivity;
import com.zqy.rxjavademo.rxjava.operator.map.FlatMapActivity;
import com.zqy.rxjavademo.rxjava.operator.map.FlatMapIterableActivity;
import com.zqy.rxjavademo.rxjava.operator.map.GroupByActivity;
import com.zqy.rxjavademo.rxjava.operator.map.MapActivity;
import com.zqy.rxjavademo.rxjava.operator.map.ScanActivity;
import com.zqy.rxjavademo.rxjava.operator.map.SwitchMapActivity;
import com.zqy.rxjavademo.rxjava.operator.map.WindowActivity;

import butterknife.OnClick;

public class OperatorMapActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_operator_map;
    }

    @Override
    protected String getTitleName() {
        return "变换操作符";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_map, R.id.btn_flatMap, R.id.btn_flatMapIterable,R.id.btn_switchMap,
            R.id.btn_scan, R.id.btn_groupBy, R.id.btn_buffer, R.id.btn_window})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_map: {
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_flatMap: {
                Intent intent = new Intent(this, FlatMapActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_flatMapIterable: {
                Intent intent = new Intent(this, FlatMapIterableActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_switchMap: {
                Intent intent = new Intent(this, SwitchMapActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_scan: {
                Intent intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_groupBy: {
                Intent intent = new Intent(this, GroupByActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_buffer: {
                Intent intent = new Intent(this, BufferActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_window: {
                Intent intent = new Intent(this, WindowActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
