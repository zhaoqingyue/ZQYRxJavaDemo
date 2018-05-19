package com.zqy.rxjavademo.rxjava.operator.merge;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

public class CombineLatestActivity extends BaseActivity {

//    @BindView(R.id.tv0)
//    TextView tv0;
//    @BindView(R.id.tv1)
//    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_combine_latest;
    }

    @Override
    protected String getTitleName() {
        return "combineLatest操作符";
    }

    @Override
    protected void initData() {
        /**
         * 当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），
         * 然后发射这个函数的结果。
         * 类似于zip，但是，不同的是zip只有在每个Observable都发射了数据才工作，
         * 而combineLatest任何一个发射了数据都可以工作，每次与另一个Observable最近的数据压合。
         */

    }
}
