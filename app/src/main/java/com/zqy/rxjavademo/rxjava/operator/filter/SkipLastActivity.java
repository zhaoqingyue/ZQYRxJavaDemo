package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class SkipLastActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_skip_last;
    }

    @Override
    protected String getTitleName() {
        return "skipLast操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("skipLast：跳过最后的N项数据或者一定时间内的数据");
        /**
         * skipLast：跳过最后的N项数据或者一定时间内的数据
         * 内部通过OperatorSkipLast和OperatorSkipLastTimed实现过滤
         */
        Observable.just(3, 4, 5, 6)
                .skipLast(2)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //3, 4
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
