package com.zqy.rxjavademo.rxbinding;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxbinding.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

public class RecyclerActivity extends BaseActivity implements RecyclerAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    RecyclerAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_recycler;
    }

    @Override
    protected String getTitleName() {
        return "RecyclerView";
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>() ;
        for ( int i=0 ; i<15 ; i++ ){
            list.add( "标题" + i ) ;
        }

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new RecyclerAdapter(list);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        RxRecyclerView.scrollStateChanges(recyclerView)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        LogUtils.d("ZQY", "change");
                        Toast.makeText(RecyclerActivity.this, "change " + integer , Toast.LENGTH_SHORT).show();
                    }
                });

        RxRecyclerViewAdapter.dataChanges(adapter)
                .subscribe(new Action1<RecyclerAdapter>() {

                    @Override
                    public void call(RecyclerAdapter adapter) {
                        LogUtils.d("ZQY", "DATA CHANGED");
                        Toast.makeText(RecyclerActivity.this, "DATA CHANGED " , Toast.LENGTH_SHORT).show();
                    }
                });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(RecyclerActivity.this, "click " + position , Toast.LENGTH_SHORT).show();
    }
}
