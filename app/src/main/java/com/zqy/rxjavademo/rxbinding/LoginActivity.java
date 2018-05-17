package com.zqy.rxjavademo.rxbinding;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_login)
    Button mBtLogin;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected String getTitleName() {
        return "登录";
    }

    @Override
    protected void initData() {
        Observable<CharSequence> ObservableName = RxTextView.textChanges(mEtPhone);
        Observable<CharSequence> ObservablePassword = RxTextView.textChanges(mEtPassword);

        Observable.combineLatest(ObservableName, ObservablePassword,
                new Func2<CharSequence, CharSequence, Boolean>() {

            @Override
            public Boolean call(CharSequence phone, CharSequence password) {
                return isPhoneValid(phone.toString()) && isPasswordValid(password.toString());
            }
        }).subscribe(new Action1<Boolean>() {

            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(mBtLogin).call(aBoolean);
            }
        });

        RxView.clicks(mBtLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {

                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(LoginActivity.this, "登录成功！" ,Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}
