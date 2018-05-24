package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class IntervalActivity extends RxBaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;

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
        /**
         * interval： 创建一个按照给定的时间间隔发射从0开始的整数序列的Observable<Long>，
         * 内部通过OnSubscribeTimerPeriodically工作。
         *
         * 轮询操作符，循环发送数据，数据从0开始递增
         */
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        //每隔1秒发送数据项，从0开始计数
                        //0, 1, 2, 3....
                        tv1.setText(aLong.toString());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
