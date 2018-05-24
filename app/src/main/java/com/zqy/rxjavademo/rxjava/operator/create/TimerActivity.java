package com.zqy.rxjavademo.rxjava.operator.create;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TimerActivity extends RxBaseActivity {

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
        tv1.setText("正在" + delay + "秒倒计时...");
        /**
         * timer： 创建一个Observable，它在一个给定的延迟时间后发射一个特殊的值--0
         * 等同于Android中Handler的postDelay( )方法
         * 内部通过OnSubscribeTimerOnce工作
         */
        Observable.timer(delay, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        Toast.makeText(getApplicationContext(),
                                 "倒计时结束",
                                Toast.LENGTH_SHORT).show();
                        tv1.setVisibility(View.INVISIBLE);
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
