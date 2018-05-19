package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;

public class CreateActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_create;
    }

    @Override
    protected String getTitleName() {
        return "create操作符";
    }

    @Override
    protected void initData() {
        /**
         * create： 使用OnSubscribe创建一个Observable
         * 注意: 使用该方法创建时，建议在OnSubscribe#call方法中检查订阅状态，以便及时停止发射数据或者运算。
         */
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("create： 使用OnSubscribe创建一个Observable");
//                    subscriber.onNext("注意: \n");
//                    subscriber.onNext("使用该方法创建时，建议在OnSubscribe#call方法中检查订阅状态\n");
//                    subscriber.onNext("检查订阅状态: subscriber.isUnsubscribed()\n");
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<String>() {

            @Override
            public void onCompleted() {
                LogUtils.dTag("ZQY", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.dTag("ZQY", "onError");
            }

            @Override
            public void onNext(String s) {
                LogUtils.dTag("ZQY", "onNext: " + s);
                tv.setText(s);
            }
        });
    }
}
