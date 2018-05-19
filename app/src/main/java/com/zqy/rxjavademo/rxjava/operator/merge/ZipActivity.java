package com.zqy.rxjavademo.rxjava.operator.merge;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func2;

public class ZipActivity extends BaseActivity {
    private Subscription subscription;

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_zip;
    }

    @Override
    protected String getTitleName() {
        return "zip操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("zip： 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果");
        /**
         * zip： 使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果
         * 如果多个Observable发射的数据量不一样，则以最少的Observable为标准进行压合
         * 内部通过OperatorZip进行压合
         */
        String str1 = "observable1-1";
        String str2 = "observable1-2";
        String str3 = "observable1-3";
        String str4 = "observable1-4";

        String str5 = "observable2-5";
        String str6 = "observable2-6";
        String str7 = "observable2-7";

        Observable<String> observable1 = Observable.just(str1, str2, str3, str4);
        Observable<String> observable2 = Observable.just(str5, str6, str7);
        Observable observable = Observable.zip(observable1, observable2, new Func2<String, String, String>() {

            @Override
            public String call(String item1, String item2) {
                return item1 + " and " + item2;
            }
        });

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
