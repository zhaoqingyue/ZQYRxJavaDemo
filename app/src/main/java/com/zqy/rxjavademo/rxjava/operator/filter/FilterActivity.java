package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class FilterActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_filter;
    }

    @Override
    protected String getTitleName() {
        return "filter操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("filter： 过滤数据\n内部通过OnSubscribeFilter过滤数据");
        /**
         * filter： 过滤数据
         * 内部通过OnSubscribeFilter过滤数据
         */
        Observable.just(3, 4, 5, 6)
                .filter(new Func1<Integer, Boolean>() {

                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 4;
                    }
                })
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
