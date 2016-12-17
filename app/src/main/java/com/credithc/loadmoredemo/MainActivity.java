package com.credithc.loadmoredemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.credithc.loadmoredemo.loadmorelistview.LoadMoreListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                adapter.notifyDataSetChanged();
                //加载成功时调用
                listView.LoadMoreSuccess();
            }else if (msg.what==0){
                //加载失败时调用
               listView.LoadMoreFail();
            }

            if (page==4){
                //分页加载完成时调用
                listView.pagingLoadComplete();
            }
        }
    };
    private boolean isFirst;
    private int page;
    private TextView textView;
    private LoadMoreListView listView;
    private ArrayList<String> data;
    private ArrayList<String> dataMore;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        //listView.addHeaderView(textView);
        listView.setAdapter(adapter);
        setLoadMore();
    }

    /**
     *  设置加载更多
     */
    private void setLoadMore() {
        //设置listView是否需要加载更多
        listView.setIsNeedLoadMore(true);
        //设置listView加载更多的监听
        listView.setLoadMoreListener(new LoadMoreListView.LoadMoreListener() {
            @Override
            public void loadMore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            dataMore=new ArrayList<String>();
                            if (!isFirst){
                                isFirst=true;
                                data.addAll(dataMore);
                                handler.sendEmptyMessage(0);
                            }else{
                                for (int i = 0; i <10; i++) {
                                    dataMore.add(String.valueOf(i));
                                }
                                data.addAll(dataMore);
                                handler.sendEmptyMessage(1);
                            }
                            page++;
                            Log.e("TAG", "loadMore111111111111111111");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void initData() {
        data = new ArrayList<String>();
        dataMore = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            data.add(String.valueOf(i));
        }
        Log.e("TAG", "dataMore"+dataMore.size());
    }

    private void initView() {
        listView = (LoadMoreListView) findViewById(R.id.listView);
        adapter = new MyAdapter(this,data);
        textView = new TextView(this);
        textView.setText("hello");
        textView.setTextSize(20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
