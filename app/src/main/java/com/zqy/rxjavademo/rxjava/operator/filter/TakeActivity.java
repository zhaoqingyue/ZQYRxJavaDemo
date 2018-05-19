package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class TakeActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_take;
    }

    @Override
    protected String getTitleName() {
        return "take操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("take：只发射开始的N项数据或者一定时间内的数据");
        /**
         * take：只发射开始的N项数据或者一定时间内的数据
         * 内部通过OperatorTake和OperatorTakeTimed过滤数据
         */
        Observable.just(3, 4, 5, 6)
                .take(3)  //发射前三个数据项
                .take(100, TimeUnit.MILLISECONDS) //发射100ms内的数据
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
