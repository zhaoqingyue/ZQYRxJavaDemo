package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class CreateActivity extends RxBaseActivity {

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
                    subscriber.onNext("我是onNext发射过来的数据");
//                    subscriber.onNext("注意: \n");
//                    subscriber.onNext("使用该方法创建时，建议在OnSubscribe#call方法中检查订阅状态\n");
//                    subscriber.onNext("检查订阅状态: subscriber.isUnsubscribed()\n");
                    subscriber.onCompleted();
                }
            }
        }).compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

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
