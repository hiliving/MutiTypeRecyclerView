package dev.yong.com.loadimage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by HY on 2017/9/26.
 */

public interface ImageLoader extends Serializable{
    void displayImage(Context context,String path,ImageView imageview);
    void clearMemoryCache();
}
