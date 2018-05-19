package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;

public class DeferActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    String str = "声明" ;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_defer;
    }

    @Override
    protected String getTitleName() {
        return "defer操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("defer：只有当订阅者订阅后才创建Observable");
        /**
         * defer：只有当订阅者订阅才创建Observable，为每个订阅创建一个新的Observable
         * 内部通过OnSubscribeDefer在订阅时调用Func0创建Observable
         */
        str = "创建defer";
        Observable<String> defer = Observable.defer(new Func0<Observable<String>>() {

            @Override
            public Observable<String> call() {
                return Observable.just(str);
            }
        });

        /**
         * 只有执行subscribe才创建Observable
         */
        str = "订阅defer" ;
        defer.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        LogUtils.d("ZQY", s);
                        tv1.setText("str：" + s);
                    }
        });
    }
}
