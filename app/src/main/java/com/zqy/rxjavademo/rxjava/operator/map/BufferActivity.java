package com.zqy.rxjavademo.rxjava.operator.map;

import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class BufferActivity extends BaseActivity {

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
        return "buffer操作符";
    }

    @Override
    protected void initData() {
        iv.setImageResource(R.mipmap.buffer);
        tv0.setText("buffer： 定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个");
        /**
         * buffer： 定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
         */
        Observable.just(2, 3, 5, 6)
                .buffer(3)
                .subscribe(new Action1<List<Integer>>() {

                    @Override
                    public void call(List<Integer> integers) {
                        for (Integer integer: integers) {
                            LogUtils.d("ZQY", integer);
                            tv1.setText(tv1.getText().toString() + "\n" + integer);
                        }
                    }
                });
    }
}
