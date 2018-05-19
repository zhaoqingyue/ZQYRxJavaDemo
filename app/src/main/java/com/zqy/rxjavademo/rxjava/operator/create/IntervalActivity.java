package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class IntervalActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    Subscription subscription ;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_interval;
    }

    @Override
    protected String getTitleName() {
        return "interval操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("interval：轮询操作符，循环发送数据，数据从0开始递增");

        /**
         * interval： 创建一个按照给定的时间间隔发射从0开始的整数序列的Observable<Long>，
         * 内部通过OnSubscribeTimerPeriodically工作。
         *
         * 轮询操作符，循环发送数据，数据从0开始递增
         */
        //Observable observable =  Observable.interval(1000, 1000, TimeUnit.MILLISECONDS);
        Observable observable =  Observable.interval(1, TimeUnit.SECONDS);
        // 防止内存泄漏，在onDestroy调用unsubscribe()；
        subscription = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        //每隔1秒发送数据项，从0开始计数
                        //0,1,2,3....
                        tv1.setText(tv1.getText().toString() + "\n" + aLong);
                    }
                });

//        Observable.interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//
//                    @Override
//                    public void call(Long aLong) {
//                        //每隔1秒发送数据项，从0开始计数
//                        //0,1,2,3....
//                        tv1.setText(tv1.getText().toString() + "\n" + aLong);
//                    }
//                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }
}
