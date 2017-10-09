package dev.yong.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.yong.com.loadimage.GlideImageLoader;

/**
 * 与BaseRecyclerAdapter一起使用，用于简化RecycleView使用
 * Created by HY on 2017/9/26.
 */
public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    private Context context;
    private final SparseArray<View> mViews;
    public  int layoutId;

    public BaseRecyclerHolder(Context context,int layoutId,View itemView) {
        super(itemView);
        this.layoutId =layoutId;
        this.mViews = new SparseArray<>(8);
        this.context = context;
    }

    public SparseArray<View> getAllView() {
        return mViews;
    }

    /**
     * @param viewId
     * @return
     */
    protected <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @param viewId
     * @param text
     * @return
     */
    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * @param viewId
     * @param visibility
     * @return
     */
    public BaseRecyclerHolder setVisible(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * @param viewId
     * @param drawableId
     * @return
     */
    public BaseRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * @param viewId
     * @param bm
     * @return
     */
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * @param url
     * @param viewId
     * @return
     */
    public BaseRecyclerHolder setImageView(String url,int viewId) {
        ImageView iv = getView(viewId);
        GlideImageLoader imageLoader = new GlideImageLoader();
        imageLoader.displayImage(context,url,iv);
        return this;
    }

    //还可添加更多控件支持....
}