package com.zqy.rxjavademo.rxjava.operator.create;

import android.widget.TextView;

import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class FromActivity extends BaseActivity {

    private CompositeSubscription compositeSubscription;

    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
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
         * from: 把其他类型的对象和数据类型转化成Observable
         * 将一个Iterable, 一个Future, 或者一个数组，内部通过代理的方式转换成一个Observable
         * Iterable通过OnSubscribeFromIterable转换
         * Future通过OnSubscribeToObservableFuture转换
         * 数组通过OnSubscribeFromArray转换
         */
        tv0.setText("from: 把其他类型的对象和数据类型转化成Observable\n" +
                "将一个Iterable, 一个Future, 或者一个数组，内部通过代理的方式转换成一个Observable");

        // Iterable
        List<String> list = new ArrayList<>();
        list.add("1. Iterable转换为Observable");
//        list.add("\n----通过OnSubscribeFromIterable转换");

//        Observable fromObservable = Observable.from(list);
//        Subscription subfrom = fromObservable.subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//
//                    @Override
//                    public void call(String s) {
//                        tv1.setText(tv1.getText().toString() + s);
//                    }
//                });
//        compositeSubscription.add(subfrom);

        Observable.from(list)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        tv1.setText(tv1.getText().toString() + s);
                    }
        });

        // Future
        Future<String> futrue = Executors.newSingleThreadExecutor().submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "2. Future转换为Observable"
//                        + "\n----通过OnSubscribeToObservableFuture转换"
                        ;
            }
        });

//        Observable futrueObservable = Observable.from(futrue);
//        Subscription subFuture = futrueObservable.subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//
//                    @Override
//                    public void call(String s) {
//                        tv2.setText(tv2.getText().toString() + s);
//                    }
//                });
//        compositeSubscription.add(subFuture);

        Observable.from(futrue)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        tv2.setText(tv2.getText().toString() + s);
                    }
        });

        // 数组
        String[] items = { "3. 数组转换为Observable"
//                , "\n----通过OnSubscribeFromArray转换"
        };

//        Observable arrayObservable = Observable.from(items);
//        Subscription subArray = arrayObservable.subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//
//                    @Override
//                    public void call(String s) {
//                        tv3.setText(tv3.getText().toString() + s);
//                    }
//                });
//        compositeSubscription.add(subArray);

        Observable.from(items)
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
        if (compositeSubscription != null)
            compositeSubscription.unsubscribe();
    }
}
