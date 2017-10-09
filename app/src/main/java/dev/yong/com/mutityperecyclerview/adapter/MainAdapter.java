package dev.yong.com.mutityperecyclerview.adapter;

import android.content.Context;

import java.util.Collection;

import dev.yong.com.BaseRecyclerAdapter;
import dev.yong.com.BaseRecyclerHolder;
import dev.yong.com.IMutlipleItem;
import dev.yong.com.mutityperecyclerview.R;
import dev.yong.com.mutityperecyclerview.bean.MainBean;

/**
 * Created by HY on 2017/9/27.
 */

public class MainAdapter extends BaseRecyclerAdapter<MainBean> {
    /**
     * 支持一种或多种Item布局
     *
     * @param context
     * @param items
     * @param datas
     */
    public MainAdapter(Context context, IMutlipleItem<MainBean> items, Collection<MainBean> datas) {
        super(context, items, datas);
    }

    @Override
    public void bindView(BaseRecyclerHolder holder, MainBean item, int position) {
        switch (position){
            case 0:
                //holder.setText(R.id.et_search,"");
                break;
            case 1:
               // holder.setImageResource(R.id.banner,R.mipmap.banner);
                break;
            case 2:
            case 3:
            case 4:
            default:
                holder.setText(R.id.img_tv,"当前内容是："+item.getContent());
                holder.setImageView("http://img1.efu.com.cn/upfile/news/picmore/2016/2016-06-16/fc28f8a4-535b-47ae-b55c-d22f30f38f80.jpg",R.id.body_left);
                break;
        }
    }
}
