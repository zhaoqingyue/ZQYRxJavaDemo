package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

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
        tv0.setText("map：对Observable发射的每一项数据都应用一个函数来变换");
        /**
         * map：对Observable发射的每一项数据都应用一个函数来变换
         */
        Observable.just(6, 2, 3)
                .map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer integer) {
                        return "item: " + integer;
                    }
                })
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        //item: 6, item: 2, item: 3
                        LogUtils.d("ZQY", s);
                        tv1.setText(tv1.getText().toString() + "\n" + s);
                    }
                });
    }
}
