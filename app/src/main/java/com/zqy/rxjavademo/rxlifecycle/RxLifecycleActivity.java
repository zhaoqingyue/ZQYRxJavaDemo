package com.zqy.rxjavademo.rxlifecycle;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxLifecycleActivity extends RxBaseActivity {

    @BindView(R.id.text_oncreate)
    TextView textOnCreate;
    @BindView(R.id.text_onstart)
    TextView textOnStart;
    @BindView(R.id.text_onresume)
    TextView textOnResume;
    @BindView(R.id.text_des)
    TextView textDes;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_rx_lifecycle;
    }

    @Override
    protected String getTitleName() {
        return "RxLifecycleA";
    }

    @Override
    protected void initData() {
        textDes.setText("1. 手动设置取消订阅的生命周期" +
                "\n.compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))" +
                "\n\n2. 手动绑定，自动取消" +
                "\n.compose(this.<Long>bindToLifecycle())" +
                "\n\n注意：compose一定要在subscribe前面执行");

        // Specifically bind this until onPause()
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(new Action0() {

                    @Override
                    public void call() {
                        LogUtils.d("ZQY", "Unsubscribing subscription from onCreate()");
                    }
                })
                .subscribeOn(Schedulers.io())
                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE)) //当Activity执行onPause()方法时解除订阅关系
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long num) {
                        LogUtils.d("ZQY", "Started in onCreate(), running until onPause(): " + num);
                        textOnCreate.setText("Started in onCreate(), running until onPause(): " + num);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("ZQY", "onStart()");
        // Using automatic unsubscription, this should determine that the correct time to
        // unsubscribe is onStop (the opposite of onStart).
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(new Action0() {

                    @Override
                    public void call() {
                        LogUtils.i("ZQY", "Unsubscribing subscription from onStart()");
                    }
                })
                .subscribeOn(Schedulers.io())
                .compose(this.<Long>bindToLifecycle()) //订阅关系跟Activity绑定，Observable和activity生命周期同步
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long num) {
                        LogUtils.i("ZQY", "Started in onStart(), running until in onStop(): " + num);
                        textOnStart.setText("Started in onStart(), running until in onStop(): " + num);
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("ZQY", "onResume()");
        // this.<Long>` is necessary if you're compiling on JDK7 or below.
        // If you're using JDK8+, then you can safely remove it.
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(new Action0() {

                    @Override
                    public void call() {
                        LogUtils.i("ZQY", "Unsubscribing subscription from onResume()");
                    }
                })
                .subscribeOn(Schedulers.io())
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))  //当Activity执行onDestroy()方法时解除订阅关系
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long num) {
                        LogUtils.i("ZQY", "Started in onResume(), running until in onDestroy(): " + num);
                        textOnResume.setText("Started in onResume(), running until in onDestroy(): " + num);
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("ZQY", "onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("ZQY", "onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("ZQY", "onDestroy()");
    }
}
