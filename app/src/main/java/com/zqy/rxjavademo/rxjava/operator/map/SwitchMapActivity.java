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

public class SwitchMapActivity extends BaseActivity {

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
        return "switchMap操作符";
    }

    @Override
    protected void initData() {
        iv.setImageResource(R.mipmap.switchmap);
        tv0.setText("switchMap：将Observable发射的数据变换为Observables集合");
        /**
         * switchMap： 和flatMap很像，将Observable发射的数据变换为Observables集合，
         * 当原始Observable发射一个新的数据（Observable）时，它将取消订阅前一个Observable
         */
        Observable.just(10, 20, 30)
                .switchMap(new Func1<Integer, Observable<Integer>>() {

                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 200;
                        if (integer > 10)
                            delay = 180;

                        return Observable.from(new Integer[]{
                                integer, integer / 2})
                                .delay(delay, TimeUnit.MILLISECONDS);
                    }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {

            @Override
            public void call(Integer integer) {
                LogUtils.d("ZQY", integer);
                tv1.setText(tv1.getText().toString() + "\n" + integer);
            }
        });
    }
}
