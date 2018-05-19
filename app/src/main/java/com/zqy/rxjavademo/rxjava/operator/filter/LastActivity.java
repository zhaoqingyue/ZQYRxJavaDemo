package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class LastActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_last;
    }

    @Override
    protected String getTitleName() {
        return "last/lastOrDefault操作符";
    }

    @Override
    protected void initData() {

        tv0.setText("first/firstOrDefault：只发射最后一项（或者满足某个条件的最后一项）数据");
        /**
         * last/lastOrDefault：只发射最后一项（或者满足某个条件的最后一项）数据
         * 可以指定默认值
         */
        Observable.just(3, 4, 5, 6)
                .last()
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //6
                        LogUtils.d("ZQY", integer);
                        tv1.setText("发射最后一项: " + integer);
                    }
                });

        Observable.just(3, 4, 5, 6)
                .last(new Func1<Integer, Boolean>() {

                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 6;
                    }
                })
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //4
                        LogUtils.d("ZQY", integer);
                        tv2.setText("发射满足某个条件的最后一项: " + integer);
                    }
                });

        Observable.just(3, 4, 5, 6)
                .lastOrDefault(8)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        LogUtils.d("ZQY", integer);
                        tv3.setText("指定默认值: " + integer);
                    }
                });
    }
}
