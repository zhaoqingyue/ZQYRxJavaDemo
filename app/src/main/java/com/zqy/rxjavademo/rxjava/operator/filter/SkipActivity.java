package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class SkipActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_skip;
    }

    @Override
    protected String getTitleName() {
        return "skip操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("skip：跳过开始的N项数据或者一定时间内的数据");
        /**
         * skip：跳过开始的N项数据或者一定时间内的数据
         * 内部通过OperatorSkip和OperatorSkipTimed实现过滤
         */
        Observable.just(3, 4, 5, 6)
                .skip(2)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //5, 6
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
