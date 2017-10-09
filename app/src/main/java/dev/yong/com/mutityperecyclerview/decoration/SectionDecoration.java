package dev.yong.com.mutityperecyclerview.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import dev.yong.com.mutityperecyclerview.bean.NameBean;

/**
 * Created by HY on 2017/9/28.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private List<NameBean> dataList;

    private DecorationCallback callback;

    private Context context;

    private Paint paint;
    private Paint textPaint;
    private final int topGap;
    private Paint.FontMetrics fontMetrics;

    private int alignBottom;

    public SectionDecoration(List<NameBean> dataList, DecorationCallback callback, Context context) {
        Resources res = context.getResources();
        this.dataList = dataList;
        this.callback = callback;
        this.context = context;
        //设置悬浮栏的画笔
        paint = new Paint();
        paint.setColor(Color.GRAY);
        //设置悬浮栏中文本的画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(14);
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextAlign(Paint.Align.LEFT);

        fontMetrics = new Paint.FontMetrics();
        //决定悬浮栏的高度
        topGap = res.getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        //决定文本显示的位置
        alignBottom = res.getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left= parent.getPaddingLeft();
        int right = parent.getWidth()-parent.getPaddingRight();
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String groupId = callback.getGroupId(position);
            if (groupId.equals("-1"))return;
            String textLine = callback.getGroupFirstLine(position).toUpperCase();
            if (textLine==""){
                float top = view.getTop();
                float bottom = view.getTop();
                c.drawRect(left,top,right,bottom,paint);
            }else {
                if (position==0||isFirstInGroup(position)){
                    float top  = view.getTop()-topGap;
                    float bottom = view.getTop();
                    //绘制悬浮栏
                    c.drawRect(left,top-topGap,right,bottom,paint);
                    //绘制文本
                    c.drawText(textLine,left,bottom,textPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth()-parent.getPaddingRight();
        float lineHeight = textPaint.getTextSize()+ fontMetrics.descent;

        String preGroupId = "";
        String groupId = "-1";
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupId = groupId;
            groupId = callback.getGroupId(position);
            if (groupId.equals("-1")||groupId.equals(preGroupId)) continue;

            String textline = callback.getGroupFirstLine(position).toUpperCase();
            if (TextUtils.isEmpty(textline)) continue;

            int viewBottom = view.getBottom();
            float textY = Math.max(topGap,view.getTop());
            //下个和当前不一样，移动到当前
            if (position+1<itemCount){
                String nextGroupId = callback.getGroupId(position+1);
                if (nextGroupId!=groupId&&viewBottom<textY){
                    textY = viewBottom;
                }
            }
            //textY-topGap 决定了悬浮栏绘制的高度和位置
            c.drawRect(left,textY-topGap,right,textY,paint);
            //left+2*alignBottom 决定了文本往左偏移的多少（加-->向左移）
            //textY-alignBottom  决定了文本往右偏移的多少  (减-->向上移)
            c.drawText(textline, left + 2 * alignBottom, textY - alignBottom, textPaint);
        }
    }

    /**
     * 判断是不是组中的第一个位置
     * @param position
     * @return
     */
    private boolean isFirstInGroup(int position) {
        if (position==0){
            return true;
        }else {
            //因为是根据字符串内容的相同与否来判断是不是同一组，所以
            //此处的标记id要是String类型。
            //如果只是做联系人列表，悬浮框里显示的只是一个字母，则标记id直接用int类型就行了
            String preGroupId = callback.getGroupId(position-1);
            String groupId = callback.getGroupId(position);
            //判断前一个字符串与当前字符串是否相同
            if (preGroupId.equals(groupId)){
                return false;
            }else {
                return true;
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        String groupId = callback.getGroupId(pos);
        if (groupId.equals("-1")){
            return;
        }else {
            outRect.top = 0;
        }
    }

    interface DecorationCallback{
        String getGroupId(int position);
        String getGroupFirstLine(int position);
    }
}
