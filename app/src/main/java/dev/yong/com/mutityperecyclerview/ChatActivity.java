package dev.yong.com.mutityperecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.yong.com.IMutlipleItem;
import dev.yong.com.OnRecyclerViewListener;
import dev.yong.com.mutityperecyclerview.adapter.ChatAdapter;
import dev.yong.com.mutityperecyclerview.bean.ChatBean;

/**
 * Created by HY on 2017/9/27.
 */

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        List<ChatBean> list = new ArrayList<>();
        ChatBean bean1 = new ChatBean();
        bean1.setType("文本内容");
        bean1.setContent("哈喽，我是小红");
        ChatBean bean2 = new ChatBean();
        bean2.setType("语音内容");
        bean2.setContent("我是语音内容");
        ChatBean bean3 = new ChatBean();
        bean3.setType("图片");
        bean3.setContent("图片url");

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        IMutlipleItem<ChatBean> mutlipleItem = new IMutlipleItem<ChatBean>() {
            @Override
            public int getItemLayoutId(int viewtype) {
               switch (viewtype){
                   case 0:
                       return R.layout.chat_item_type1;
                   case 1:
                       return R.layout.chat_item_type2;
                   default:
                       return R.layout.chat_item_type3;
               }
            }

            @Override
            public int getItemViewType(int postion, ChatBean chatBean) {
                return postion;
            }

            @Override
            public int getItemCount(List<ChatBean> list) {
                return list.size();
            }
        };
        adapter = new ChatAdapter(this,mutlipleItem,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setListener();

    }

    private void setListener() {
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ChatActivity.this, "当前点击了"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }
}
