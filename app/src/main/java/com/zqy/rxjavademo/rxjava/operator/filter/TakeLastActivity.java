package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class TakeLastActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_take_last;
    }

    @Override
    protected String getTitleName() {
        return "takeLast操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("takeLast： 只发射最后的N项数据或者一定时间内的数据");
        /**
         * takeLast： 只发射最后的N项数据或者一定时间内的数据
         * 内部通过OperatorTakeLast和OperatorTakeLastTimed过滤数据
         */
        Observable.just(3, 4, 5, 6)
                .takeLast(3)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //4, 5, 6
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
