package com.zqy.rxjavademo.rxjava;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

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

        // 1. 创建Observable：事件源对象，被观察者 ---- 发射数据
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> sub) {
                sub.onNext("1. 创建Observable\n");
                sub.onNext("2. 创建Subscriber\n");
                sub.onNext("3. 关联Observable与Subscriber");
                sub.onCompleted();
            }
        });

        // 2. 创建Subscriber：接收源对象，观察者 ---- 接收数据
        Subscriber<String> mySubscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

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

        // 或创建Observer
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        /**
         * 关联Observable与Subscriber
         * 一旦mySubscriber订阅了myObservable，
         * myObservable就调用mySubscriber对象的onNext和onComplete方法
         */
        // 3. 关联发射源和接收源
        myObservable.subscribe(mySubscriber) ;

        /**
         * 如果不在意数据是否接收完或者是否出现错误，即不需要Observer的onCompleted()和onError()方法，可使用Action1
         * subscribe()支持将Action1作为参数传入,RxJava将会调用它的call方法来接收数据
         */
        myObservable.subscribe(new Action1<String>() {

            @Override
            public void call(String s) {

            }
        });



//       AsyncSubject<String> asyncSubject = AsyncSubject.create();
    }
}
