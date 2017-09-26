package dev.yong.com.loadimage;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dev.yong.com.loadimage.ImageLoader;

/**
 * Created by HY on 2017/9/26.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage( Context context, String path, ImageView imageview) {
        Glide.with(context)
                .load(path)
                .placeholder(android.R.mipmap.sym_def_app_icon)
                .centerCrop()
                .into(imageview);
    }

    @Override
    public void clearMemoryCache() {

    }
}
