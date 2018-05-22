package com.zqy.rxjavademo.rxbinding;

import android.widget.Button;
import android.widget.CheckBox;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.functions.Action1;

public class CheckBoxActivity extends BaseActivity {

    @BindView(R.id.checkbox)
    CheckBox checkBox;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_check_box;
    }

    @Override
    protected String getTitleName() {
        return "CheckBox";
    }

    @Override
    protected void initData() {
        RxCompoundButton.checkedChanges(checkBox)
                .subscribe(new Action1<Boolean>() {

                    @Override
                    public void call(Boolean aBoolean) {
                        button.setClickable(aBoolean);
                        button.setBackgroundResource(aBoolean ? R.color.colorEnable : R.color.colorDisable);
                    }
                });
    }
}
