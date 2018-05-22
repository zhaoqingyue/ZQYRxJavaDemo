package com.zqy.rxjavademo;

import android.content.Intent;
import android.view.View;

import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxbinding.ButtonActivity;
import com.zqy.rxjavademo.rxbinding.CheckBoxActivity;
import com.zqy.rxjavademo.rxbinding.DebounceActivity;
import com.zqy.rxjavademo.rxbinding.EditTextActivity;
import com.zqy.rxjavademo.rxbinding.ListActivity;
import com.zqy.rxjavademo.rxbinding.LoginActivity;
import com.zqy.rxjavademo.rxbinding.RecyclerActivity;
import com.zqy.rxjavademo.rxbinding.SeekBarActivity;
import com.zqy.rxjavademo.rxbinding.VerifyCodeActivity;

import butterknife.OnClick;

public class RxBindingActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_rx_binding;
    }

    @Override
    protected String getTitleName() {
        return "RxBinding";
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_button, R.id.btn_edittext, R.id.btn_checkbox, R.id.btn_seekbar,
              R.id.btn_list, R.id.btn_recycler, R.id.btn_debounce, R.id.btn_verifycode, R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_button: {
                Intent intent = new Intent(this, ButtonActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_edittext: {
                Intent intent = new Intent(this, EditTextActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_checkbox: {
                Intent intent = new Intent(this, CheckBoxActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_list: {
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_recycler: {
                Intent intent = new Intent(this, RecyclerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_seekbar: {
                Intent intent = new Intent(this, SeekBarActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_debounce: {
                Intent intent = new Intent(this, DebounceActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_verifycode: {
                Intent intent = new Intent(this, VerifyCodeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_login: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
