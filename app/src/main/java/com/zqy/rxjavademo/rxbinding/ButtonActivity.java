package com.zqy.rxjavademo.rxbinding;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Button点击事件（TextView的点击事件与Button一样）
 */
public class ButtonActivity extends BaseActivity {

    private CompositeSubscription mSubscription;

    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_button;
    }

    @Override
    protected String getTitleName() {
        return "Button";
    }

    @Override
    protected void initData() {
        mSubscription = new CompositeSubscription();

        // Button的OnClickListener点击事件
        Subscription buttonSub = RxView.clicks(btn0).subscribe(new Action1<Void>() {

            @Override
            public void call(Void aVoid) {
                Toast.makeText(ButtonActivity.this, "Button点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        mSubscription.add(buttonSub);

        // 防止多次点击
        /**
         * 操作符throttleFirst: 取时间间隔内第一次点击事件
         */
        RxView.clicks(btn1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {

                    @Override
                    public void call(Void aVoid) {
                        tv1.setVisibility(View.VISIBLE);
                        RxTextView.text(tv1).call("----button clicked----");
                        LogUtils.dTag("ZQY", "----button clicked----");
                    }
                });

        // Button多次监听
        /**
         * Android是不能多次监听同一个点击事件。
         * 但利用RxJava的操作符，例如publish, share或replay可以实现。
         * 而RxBinding恰好支持对点击事件的多次监听。
         */
        Observable<Void> observable= RxView.clicks(btn2).share();
        Subscription sub1 = observable.subscribe(new Action1<Void>() {

            @Override
            public void call(Void aVoid) {
                tv2.setVisibility(View.VISIBLE);
                RxTextView.text(tv2).call("Button监听" + "--第一次监听");
            }
        });
        mSubscription.add(sub1);

        Subscription sub2 = observable.subscribe(new Action1<Void>() {

            @Override
            public void call(Void aVoid) {
                tv2.setVisibility(View.VISIBLE);
                String text = tv2.getText().toString();
                RxTextView.text(tv2).call(text + "\n" + "Button监听" + "--第二次监听");
            }
        });
        mSubscription.add(sub2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
