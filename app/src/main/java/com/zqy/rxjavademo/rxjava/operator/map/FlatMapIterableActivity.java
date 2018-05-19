package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.Arrays;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class FlatMapIterableActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common;
    }

    @Override
    protected String getTitleName() {
        return "flatMapIterable操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("flatMapIterable： 和flatMap的作用一样，只不过生成的是Iterable而不是Observable");
        /**
         * flatMapIterable： 和flatMap的作用一样，只不过生成的是Iterable而不是Observable
         */
        Observable.just(2, 3, 5)
                .flatMapIterable(new Func1<Integer, Iterable<String>>() {

                    @Override
                    public Iterable<String> call(Integer integer) {
                        return Arrays.asList(integer*10 + "", integer*100 + "");
                    }
                })
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        //20,200,30,300,50,500
                        LogUtils.d("ZQY", s);
                        tv1.setText(tv1.getText().toString() + "\n" + s);
                    }
                });
    }
}
