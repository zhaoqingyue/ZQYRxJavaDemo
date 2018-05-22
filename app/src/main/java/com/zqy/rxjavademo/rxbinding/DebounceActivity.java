package com.zqy.rxjavademo.rxbinding;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.zqy.rxjavademo.R;
import com.zqy.rxjavademo.base.BaseActivity;
import com.zqy.rxjavademo.rxbinding.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DebounceActivity extends BaseActivity {

    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.listview)
    ListView listView;

    ListAdapter adapter;
    List<String> list;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_debounce;
    }

    @Override
    protected String getTitleName() {
        return "防抖动";
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new ListAdapter(list);
        listView.setAdapter(adapter);

        RxTextView.textChanges(edittext)
                .debounce(600 , TimeUnit.MILLISECONDS) // debounce()在一定的时间内没有操作就会发送事件
                .map(new Func1<CharSequence, String>() {

                    @Override
                    public String call(CharSequence charSequence) {
                        // get the keyword
                        String key = charSequence.toString() ;
                        return key ;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<String>>() {

                    @Override
                    public List<String> call(String keyWord) {
                        // get list
                        List<String> dataList = new ArrayList<String>() ;
                        if ( ! TextUtils.isEmpty(keyWord)){
                            for (String s : getData()) {
                                if (s != null) {
                                    if (s.contains(keyWord)) {
                                        dataList.add(s);
                                    }
                                }
                            }
                        }
                        return dataList ;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {

                    @Override
                    public void call(List<String> strings) {
                        // update list
                        list.clear();
                        list.addAll(strings);
                        adapter.notifyDataSetChanged();
                    }
                }) ;
    }

    public  List<String> getData(){
        List<String> list = new ArrayList<>() ;
        list.add( "a" ) ;
        list.add( "aab" ) ;
        list.add( "aaabc" ) ;
        list.add( "aaaaabcd" ) ;
        list.add( "aaaaaabcde" ) ;
        list.add( "aaaaaaac" ) ;
        list.add( "aaaaaaaacd" ) ;
        list.add( "aaaaaaaaacdef" ) ;
        list.add( "aaaaaaaaaabc" ) ;
        list.add( "bcd" ) ;
        list.add( "bcdef" ) ;
        list.add( "cd" ) ;
        list.add( "cde" ) ;
        list.add( "cdefg" ) ;
        list.add( "dddkkk5662" ) ;
        list.add( "bbs" ) ;
        list.add( "7b4s" ) ;
        list.add( "jjjsssd995556214613" ) ;
        list.add( "58350675" ) ;

        return  list ;
    }
}
