package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class ScanActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common;
    }

    @Override
    protected String getTitleName() {
        return "scan操作符";
    }

    @Override
    protected void initData() {
        iv.setImageResource(R.mipmap.scan);
        tv0.setText("scan： 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值");
        /**
         * scan： 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值
         */
        Observable.just(2, 4, 7)
                .scan(new Func2<Integer, Integer, Integer>() {

                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum + item;
                    }
                })
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //2, 6, 13
                        LogUtils.d("ZQY", integer);
                        tv1.setText(tv1.getText().toString() + "\n" + integer);
                    }
                });
    }
}
