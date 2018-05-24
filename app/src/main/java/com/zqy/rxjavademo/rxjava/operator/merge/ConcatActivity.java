package com.zqy.rxjavademo.rxjava.operator.merge;

import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ConcatActivity extends RxBaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_concat;
    }

    @Override
    protected String getTitleName() {
        return "concat操作符";
    }

    @Override
    protected void initData() {
        /**
         * concat: 按顺序连接多个Observables
         * Observable.concat(a,b)等价于a.concatWith(b)
         */
        String str1 = "observable1-1";
        String str2 = "observable1-2";
        String str3 = "observable1-3";
        String str4 = "observable1-4";
        String str5 = "observable2-4";
        String str6 = "observable2-5";
        String str7 = "observable2-6";

        Observable<String> observable1 = Observable.just(str1, str2, str3, str4);
        Observable<String> observable2 = Observable.just(str5, str6, str7);
        Observable observable = Observable.concat(observable1, observable2);
       observable.compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                tv1.setText(tv1.getText().toString() + "\n" + s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
