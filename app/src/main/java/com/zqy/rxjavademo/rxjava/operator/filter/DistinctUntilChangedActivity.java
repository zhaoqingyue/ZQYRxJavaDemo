package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class DistinctUntilChangedActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_distinct_until_changed;
    }

    @Override
    protected String getTitleName() {
        return "distinctUntilChanged操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("distinctUntilChanged：过滤掉连续重复的数据");
        /**
         * distinctUntilChanged：过滤掉连续重复的数据
         * 内部通过OperatorDistinctUntilChanged实现
         */
        Observable.just(3, 4, 5, 6, 3, 3, 4, 9)
                .distinctUntilChanged()
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //3, 4, 5, 6, 3, 4, 9
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
