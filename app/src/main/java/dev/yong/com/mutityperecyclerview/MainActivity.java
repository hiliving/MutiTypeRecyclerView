package dev.yong.com.mutityperecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.yong.com.IMutlipleItem;
import dev.yong.com.OnRecyclerViewListener;
import dev.yong.com.mutityperecyclerview.adapter.ChatAdapter;
import dev.yong.com.mutityperecyclerview.adapter.MainAdapter;
import dev.yong.com.mutityperecyclerview.adapter.MyAdapter;
import dev.yong.com.mutityperecyclerview.bean.MainBean;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainrv;
    private MainAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    private List<MainBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainrv = (RecyclerView) findViewById(R.id.main_rv);
        list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            MainBean bean= new MainBean();
            bean.setContent("内容"+i);
            list.add(bean);
        }

        IMutlipleItem<MainBean> mutlipleItem = new IMutlipleItem<MainBean>() {
            @Override
            public int getItemLayoutId(int viewtype) {
                switch (viewtype){
                    case 0:
                        return R.layout.main_item_type_head1;
                    case 1:
                        return R.layout.main_item_type_head2;
                    default:
                        return R.layout.main_item_type_body;
                }
            }

            @Override
            public int getItemViewType(int postion, MainBean mainBean) {
                return postion;
            }

            @Override
            public int getItemCount(List<MainBean> list) {
                return list.size();
            }
        };
        adapter = new MainAdapter(this,mutlipleItem, list);
        mainrv.setAdapter(adapter);
        mainrv.setLayoutManager(new LinearLayoutManager(this));

        setListener();
    }

    private void setListener() {
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "当前点击的是"+position, Toast.LENGTH_SHORT).show();
                if (position>=2){
                    startActivity(new Intent(MainActivity.this, ChatActivity.class));
                }
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

}
