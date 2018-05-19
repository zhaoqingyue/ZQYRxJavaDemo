package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class WindowActivity extends BaseActivity {

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
        return "window操作符";
    }

    @Override
    protected void initData() {
        iv.setImageResource(R.mipmap.window);
        tv0.setText("window： 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。");
        /**
         * window： 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。
         */
        Observable.just(2, 3, 5, 6)
                .window(3)
                .subscribe(new Action1<Observable<Integer>>() {

                    @Override
                    public void call(Observable<Integer> integerObservable) {

                        integerObservable.subscribe(new Action1<Integer>() {

                            @Override
                            public void call(Integer integer) {
                                LogUtils.d("ZQY", integer);
                                tv1.setText(tv1.getText().toString() + "\n" + integer);
                            }
                        });
                    }
                });
    }
}
