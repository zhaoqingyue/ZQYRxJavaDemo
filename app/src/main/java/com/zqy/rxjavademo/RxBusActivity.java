package com.zqy.rxjavademo;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxbus.RxBus;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RxBusActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

    String tag = "tag" ;
    Observable<String> ob ;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_rx_bus;
    }

    @Override
    protected String getTitleName() {
        return "RxBus";
    }

    @Override
    protected void initData() {
        //创建被观察者
        ob = RxBus.get().register( tag , String.class ) ;
        //订阅观察事件
        ob.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String s) {
                        LogUtils.e(tag, s);
                        tv.setText(s);
                    }
                }) ;

        //发送内容
        RxBus.get().post(  tag , "我是通过RxBus发送过来的消息" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        RxBus.get().unregister( tag , ob );
    }
}
