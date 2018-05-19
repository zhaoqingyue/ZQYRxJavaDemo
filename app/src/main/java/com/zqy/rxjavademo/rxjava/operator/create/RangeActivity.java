package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RangeActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_range;
    }

    @Override
    protected String getTitleName() {
        return "rang操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("range： 创建一个发射指定范围的整数序列的Observable<Integer>");
        /**
         * range： 创建一个发射指定范围的整数序列的Observable<Integer>
         * 两个参数:  第一个是范围的起始值，第二个是范围的数据的数目
         */
        Observable.range(2, 5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        // 2,3,4,5,6 从2开始发射5个数据
                        LogUtils.d("ZQY", integer.toString());
                        tv1.setText(tv1.getText().toString() + "\n" + integer.toString());
                    }
                });
    }
}
