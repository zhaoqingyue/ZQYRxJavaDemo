package com.zqy.rxjavademo.rxjava.operator.merge;

import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MergeActivity extends RxBaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_merge;
    }

    @Override
    protected String getTitleName() {
        return "merge操作符";
    }

    @Override
    protected void initData() {
        /**
         * merge: 合并观察对象, 将多个Observable合并为一个
         * merge不是按照添加顺序连接，而是按照时间线来连接
         * merge遇到异常将停止发射数据，发送onError通知。
         */

        List<String> list1 = new ArrayList<>() ;
        List<String> list2 = new ArrayList<>() ;

        list1.add( "observable1-1" );
        list1.add( "observable1-2" );
        list1.add( "observable1-3" );

        list2.add( "observable2-a" );
        list2.add( "observable2-b" );
        list2.add( "observable2-c" );

        Observable observable1 = Observable.from(list1);
        Observable observable2 = Observable.from(list2);

        //合并数据  先发送observable2的全部数据，然后发送observable1的全部数据
        Observable.merge(observable2, observable1)
                .compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        tv1.setText(tv1.getText().toString() + "\n" + s);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
