package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TimerActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;
    int delay = 3;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_timer;
    }

    @Override
    protected String getTitleName() {
        return "timer操作符";
    }

    @Override
    protected void initData() {

        /**
         * timer： 创建一个Observable，它在一个给定的延迟时间后发射一个特殊的值--0
         * 等同于Android中Handler的postDelay( )方法
         * 内部通过OnSubscribeTimerOnce工作
         */
        tv0.setText("我的初始值为：" + delay);
        tv1.setText(delay + "秒之后，我的值为：");
        Observable.timer(delay, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread() )
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        LogUtils.dTag("ZQY", "call: " + aLong);
                        tv1.setText(delay + "秒之后，我的值为：" + aLong);
                    }
                });

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//            }
//        }, 3000);
    }
}
