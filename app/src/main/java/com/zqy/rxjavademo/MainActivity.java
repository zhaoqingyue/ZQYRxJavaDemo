package com.zqy.rxjavademo;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleName() {
        return getResources().getString(R.string.app_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isHasNaviIcon() {
        return false;
    }

    @OnClick({R.id.btn_rxjava, R.id.btn_rxandroid, R.id.btn_rxbinding, R.id.btn_rxbus})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rxjava: {
                Intent intent = new Intent(this, RxJavaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_rxandroid: {
                break;
            }
            case R.id.btn_rxbinding: {
                Intent intent = new Intent(this, RxBindingActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_rxbus: {
                Intent intent = new Intent(this, RxBusActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
