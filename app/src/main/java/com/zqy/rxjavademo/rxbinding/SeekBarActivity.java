package com.zqy.rxjavademo.rxbinding;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxSeekBar;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

public class SeekBarActivity extends BaseActivity {

    @BindView(R.id.activity_seek_bar)
    LinearLayout linearLayout;

    @BindView(R.id.seekbar)
    SeekBar seekbar;

    @BindView(R.id.tv_alpha)
    TextView tv_alpha;

    Subscription seekBarSub;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_seek_bar;
    }

    @Override
    protected String getTitleName() {
        return "SeekBar";
    }

    @Override
    protected void initData() {
        // SeekBar的OnSeekBarChangeListener事件
        seekBarSub = RxSeekBar.userChanges(seekbar).subscribe(new Action1<Integer>() {

            @Override
            public void call(Integer seekValue) {
                linearLayout.setBackgroundColor(Color.argb(seekValue, 131, 255, 8));
                tv_alpha.setText("alpha: " + seekValue);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (seekBarSub != null)
            seekBarSub.unsubscribe();
    }
}
