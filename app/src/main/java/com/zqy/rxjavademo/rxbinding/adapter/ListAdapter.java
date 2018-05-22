package com.zqy.rxjavademo.rxbinding.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zqy.rxjavademo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhaoqy on 2018/5/22.
 */

public class ListAdapter extends BaseAdapter {

    private List<String> mData;

    public ListAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(mData.get(position));
        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.tv_title)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
