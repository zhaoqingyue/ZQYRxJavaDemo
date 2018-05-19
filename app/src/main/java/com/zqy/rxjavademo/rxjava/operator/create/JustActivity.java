package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class JustActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_just;
    }

    @Override
    protected String getTitleName() {
        return "just操作符";
    }

    @Override
    protected void initData() {
        /**
         * just： 将一个或多个对象转换成发射这个或这些对象的一个Observable
         * 如果是单个对象，内部创建的是ScalarSynchronousObservable对象
         * 如果是多个对象，则是调用了from方法创建
         *
         * 使用just( )，将创建一个Observable并自动调用onNext( )发射数据
         */

        String str1 = "1. 将创建一个Observable\n";
        String str2 = "2. 自动调用onNext( )发射数据";
        Observable.just(str1, str2)
                .subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                tv0.setText(tv0.getText().toString() + s);
            }
        });
    }
}
