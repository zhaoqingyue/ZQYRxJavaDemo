package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.RxBaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class FromActivity extends RxBaseActivity {

    @BindView(R.id.tv3)
    TextView tv3;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_from;
    }

    @Override
    protected String getTitleName() {
        return "from操作符";
    }

    @Override
    protected void initData() {
        /**
         * from: 将一个Iterable, 一个Future, 或者一个数组，内部通过代理的方式转换成一个Observable
         * Iterable：通过OnSubscribeFromIterable转换
         * Future：通过OnSubscribeToObservableFuture转换
         * 数组：通过OnSubscribeFromArray转换
         */

        // Iterable
        List<String> list = new ArrayList<>();
        list.add("1. Iterable转换为Observable");

//        Observable.from(list)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//
//                    @Override
//                    public void call(String s) {
//                        tv1.setText(s);
//                    }
//        });

        // Future
        Future<String> futrue = Executors.newSingleThreadExecutor().submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "2. Future转换为Observable";
            }
        });

//        Observable.from(futrue)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//
//                    @Override
//                    public void call(String s) {
//                        tv2.setText(s);
//                    }
//        });

        // 数组
        String[] items = { "1. Iterable转换为Observable\n",
                "2. Future转换为Observable\n",
                "3. 数组转换为Observable" };
        Observable.from(items)
                .compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        tv3.setText(tv3.getText().toString() + s);
                    }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
