package com.zqy.rxjavademo.rxjava.operator.merge;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class StartWithActivity extends BaseActivity {
    private Subscription subscription;

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_start_with;
    }

    @Override
    protected String getTitleName() {
        return "startWith操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("startWith： 在数据序列的开头增加一项数据");
        /**
         * startWith： 在数据序列的开头增加一项数据
         * startWith的内部也是调用了concat
         */
        String str1 = "observable1-1";
        String str2 = "observable1-2";
        String str3 = "observable1-3";
        String str4 = "observable1-4";

        String str5 = "startWith-5";
        String str6 = "startWith-6";
        String str7 = "startWith-7";

        Observable observable = Observable.just(str1, str2, str3, str4)
                .startWith(str5, str6, str7);
        subscription = observable.subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                LogUtils.d("ZQY", s);
                tv1.setText(tv1.getText().toString() + "\n" + s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }
}
