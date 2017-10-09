package dev.yong.com.mutityperecyclerview.adapter;

import android.content.Context;

import java.util.Collection;

import dev.yong.com.BaseRecyclerAdapter;
import dev.yong.com.BaseRecyclerHolder;
import dev.yong.com.IMutlipleItem;
import dev.yong.com.mutityperecyclerview.R;
import dev.yong.com.mutityperecyclerview.bean.ChatBean;

/**
 * Created by HY on 2017/9/27.
 */

public class ChatAdapter extends BaseRecyclerAdapter<ChatBean> {

    /**
     * 支持一种或多种Item布局
     *
     * @param context
     * @param items
     * @param datas
     */
    public ChatAdapter(Context context, IMutlipleItem<ChatBean> items, Collection<ChatBean> datas) {
        super(context, items, datas);
    }

    @Override
    public void bindView(BaseRecyclerHolder holder, ChatBean item, int position) {
        if (position==0){
            holder.setText(R.id.chat_tv_type1_title,item.getType());
            holder.setText(R.id.chat_tv_type1_content,item.getContent());
        }else if (position==1){
            holder.setText(R.id.chat_tv_type2_title,item.getType());
            holder.setText(R.id.chat_tv_type2_content,item.getContent());
        }else {
            holder.setText(R.id.chat_tv_type3_title,item.getType());
            holder.setText(R.id.chat_tv_type3_content,item.getContent());
        }
    }
}
