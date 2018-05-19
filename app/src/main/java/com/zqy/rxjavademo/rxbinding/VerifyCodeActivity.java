package com.zqy.rxjavademo.rxbinding;

import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class VerifyCodeActivity extends BaseActivity {

    private static int SECOND = 20;

    @BindView(R.id.bt)
    Button mBt;

    private Observable<Void> verifyCodeObservable;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_verify_code;
    }

    @Override
    protected String getTitleName() {
        return "验证码";
    }

    @Override
    protected void initData() {
        verifyCodeObservable = RxView.clicks(mBt)
                .throttleFirst(SECOND, TimeUnit.SECONDS) //防止20秒内连续点击,或者只使用doOnNext部分
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Void>() {

                    @Override
                    public void call(Void aVoid) {
                        RxView.enabled(mBt).call(false);
                    }
                });

        verifyCodeObservable.subscribe(new Action1<Void>() {

            @Override
            public void call(Void aVoid) {
                Observable.interval(1, TimeUnit.SECONDS,
                        AndroidSchedulers.mainThread())
                        .take(SECOND)
                        .subscribe(new Observer<Long>() {

                            @Override
                            public void onCompleted() {
                                RxTextView.text(mBt).call("获取验证码");
                                RxView.enabled(mBt).call(true);
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.e("ZQY", e.toString());
                            }

                            @Override
                            public void onNext(Long aLong) {
                                RxTextView.text(mBt).call("剩余" + (SECOND - aLong) + "秒");
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        verifyCodeObservable.unsubscribeOn(AndroidSchedulers.mainThread()); //防止泄露
    }
}
