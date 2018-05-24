package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RepeatActivity extends RxBaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_repeat;
    }

    @Override
    protected String getTitleName() {
        return "repeat操作符";
    }

    @Override
    protected void initData() {
        /**
         *  创建一个重复发射指定数据或数据序列的Observable
         */
        Observable.just("one", "two", "three")
                .repeat(3)
                .compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        tv1.setText(tv1.getText().toString() + "\n" + s);
                    }
                });
    }
}
