package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class OfTypeActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_of_type;
    }

    @Override
    protected String getTitleName() {
        return "ofType操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("ofType： 过滤指定类型的数据");
        /**
         * ofType： 过滤指定类型的数据
         * 与filter类似
         */
        Observable.just(1, 2, "3")
                .ofType(Integer.class)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //1, 2
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
