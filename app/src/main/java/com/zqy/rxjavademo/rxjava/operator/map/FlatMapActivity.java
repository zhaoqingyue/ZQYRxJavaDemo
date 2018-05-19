package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class FlatMapActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common;
    }

    @Override
    protected String getTitleName() {
        return "flatMap操作符";
    }

    @Override
    protected void initData() {
        iv.setImageResource(R.mipmap.flatmap);
        tv0.setText("flatMap： 将Observable发射的数据变换为Observables集合");
        /**
         * flatMap： 将Observable发射的数据变换为Observables集合，
         * 然后将这些Observable发射的数据平坦化的放进一个单独的Observable，
         * 内部采用merge合并
         */
        Observable.just(10, 20, 30)
                .flatMap(new Func1<Integer, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 200;
                        if (integer > 10)
                            delay = 180;

                        return Observable.from(new Integer[]{
                                integer, integer / 2}
                        ).delay(delay, TimeUnit.MILLISECONDS);
                    }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
