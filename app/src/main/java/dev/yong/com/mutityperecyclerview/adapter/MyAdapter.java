package dev.yong.com.mutityperecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import dev.yong.com.mutityperecyclerview.R;
import dev.yong.com.mutityperecyclerview.bean.MainBean;

/**
 * Created by HY on 2017/9/27.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private List<MainBean> list;
    public MyAdapter(List<MainBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_type_head1,null);
            return new MHolder(view);
        }else if (viewType==1){
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_type_head2,null);
            return new MHolder(view);
        }else {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_type_body,null);
            return new MHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position==0){

        }else if (position==1){

        }  else {

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MHolder extends RecyclerView.ViewHolder{

        public MHolder(View itemView) {
            super(itemView);
        }
    }
}
