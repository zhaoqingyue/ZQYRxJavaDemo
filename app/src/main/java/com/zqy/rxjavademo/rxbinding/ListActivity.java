package com.zqy.rxjavademo.rxbinding;

import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxbinding.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

public class ListActivity extends BaseActivity {

    @BindView(R.id.listview)
    ListView listView;

    ListAdapter adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_list;
    }

    @Override
    protected String getTitleName() {
        return "ListView";
    }

    @Override
    protected void initData() {

        List<String> list = new ArrayList<>();
        for ( int i=0 ; i<10 ; i++ ){
            list.add( "标题" + i ) ;
        }

        adapter = new ListAdapter(list);
        listView.setAdapter(adapter);

        //item click event
        RxAdapterView.itemClicks( listView )
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(ListActivity.this, "item click " + integer , Toast.LENGTH_SHORT).show();
                    }
                }) ;

        //item long click
        RxAdapterView.itemLongClicks( listView)
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(ListActivity.this, "item long click " + integer , Toast.LENGTH_SHORT).show();
                    }
                }) ;
    }
}
