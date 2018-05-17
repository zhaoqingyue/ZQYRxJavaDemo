package com.zqy.rxjavademo.rxbinding;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class EditTextActivity extends BaseActivity {

    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.tv_input)
    TextView tv_input;

    Subscription editTextSub;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_edit_text;
    }

    @Override
    protected String getTitleName() {
        return "EditText";
    }

    @Override
    protected void initData() {
        // EditText的TextWatcher文本改变事件
        editTextSub = RxTextView.textChanges(et)
                .map(new Func1<CharSequence, String>() {

                    @Override
                    public String call(CharSequence charSequence) {
                        return new StringBuilder(charSequence).toString().toLowerCase();
                    }
                })
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String value) {
                        if (!TextUtils.isEmpty(value)) {
                            tv_input.setText("输入的内容为: " + value);
                        } else {
                            tv_input.setText("");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (editTextSub != null)
            editTextSub.unsubscribe();
    }
}
