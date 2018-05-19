package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class GroupByActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common;
    }

    @Override
    protected String getTitleName() {
        return "groupBy操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("groupBy： 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，" +
                "每一个Observable发射一组不同的数据");
        /**
         * groupBy： 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，
         * 每一个Observable发射一组不同的数据。
         */
        Observable.just(2, 3, 5, 6)
                .groupBy(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer integer) {
                        //分组
                        return integer%2 == 0 ? "偶数" : "奇数";
                    }
                })
                .subscribe(new Action1<GroupedObservable<String, Integer>>() {

                    @Override
                    public void call(final GroupedObservable<String, Integer> o) {

                        o.subscribe(new Action1<Integer>() {

                            @Override
                            public void call(Integer integer) {
                                //偶数：2，奇数：3，...
                                LogUtils.d("ZQY", integer);
                                tv1.setText(tv1.getText().toString() + "\n" + o.getKey()+": " + integer);
                            }
                        });
                    }
                });
    }
}
