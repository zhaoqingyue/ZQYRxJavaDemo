package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class JustActivity extends RxBaseActivity {

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
        String str0 = "特点：";
        String str1 = "1. 将创建一个Observable";
        String str2 = "2. 自动调用onNext( )发射数据";
        Observable.just(str0, str1, str2)
                .compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                tv0.setText(tv0.getText().toString() + "\n" + s);
            }
        });
    }
}
