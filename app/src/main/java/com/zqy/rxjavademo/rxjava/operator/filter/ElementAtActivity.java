package com.zqy.rxjavademo.rxjava.operator.filter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class ElementAtActivity extends BaseActivity {

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_element_at;
    }

    @Override
    protected String getTitleName() {
        return "elementAt操作符";
    }

    @Override
    protected void initData() {
        tv0.setText("elementAt/elementAtOrDefault：发射某一项数据，如果超过了范围可以的指定默认值");
        /**
         * elementAt/elementAtOrDefault：发射某一项数据，如果超过了范围可以的指定默认值
         * 内部通过OperatorElementAt过滤
         */
        Observable.just(3, 4, 5, 6)
                .elementAt(2)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        //5
                        LogUtils.d("ZQY", integer);
                        tv1.setText(integer.toString());
                    }
                });
    }
}
