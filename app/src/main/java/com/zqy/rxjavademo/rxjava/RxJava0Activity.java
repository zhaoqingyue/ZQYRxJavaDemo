package com.zqy.rxjavademo.rxjava;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;

public class RxJava0Activity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_rx_java0;
    }

    @Override
    protected String getTitleName() {
        return "基本用法";
    }

    @Override
    protected void initData() {

        // 创建Observable：事件源对象，被观察者
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> sub) {
                sub.onNext("1. 创建Observable\n");
                sub.onNext("2. 创建Subscriber\n");
                sub.onNext("3. 关联Observable与Subscriber");
                sub.onCompleted();
            }
        });

        // 创建Subscriber：接收源对象，观察者
        Subscriber<String> mySubscriber = new Subscriber<String>() {

            @Override
            public void onNext(String s) {
                LogUtils.dTag("ZQY", s);
                tv.setText(tv.getText().toString() + s);
            }

            @Override
            public void onCompleted() {
                LogUtils.dTag("ZQY", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        /**
         * 关联Observable与Subscriber
         * 一旦mySubscriber订阅了myObservable，
         * myObservable就调用mySubscriber对象的onNext和onComplete方法
         */
        myObservable.subscribe(mySubscriber) ;
    }
}
