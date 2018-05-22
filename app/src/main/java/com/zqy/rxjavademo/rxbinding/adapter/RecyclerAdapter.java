package com.zqy.rxjavademo.rxbinding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zqy.rxjavademo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhaoqy on 2018/5/22.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<String> mData;

    public RecyclerAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String string = mData.get(position);
        holder.title.setText(string);
        holder.title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.title, pos);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView title;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private RecyclerAdapter.OnItemClickListener onItemClickListener;

    public void updateData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }
}
