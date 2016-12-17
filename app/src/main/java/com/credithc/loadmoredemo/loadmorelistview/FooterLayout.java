package com.credithc.loadmoredemo.loadmorelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.credithc.loadmoredemo.R;

/**
 *  Created by zgj on 2015/10/10-11:04
 *  加载更多ListView的尾布局
 */
public class FooterLayout extends RelativeLayout implements LmFooterHandler{
    private TextView footer_textView;
    private ProgressBar footer_progressBar;
    private RelativeLayout rootView;

    /**
     * 构造器
     * @param context
     */
    public FooterLayout(Context context) {
       this(context,null);
    }

    public FooterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化试图
     * @param context
     */
    private void initView(Context context) {
        rootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.footer, this,false);
        addView(rootView);
        rootView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        footer_progressBar = (ProgressBar) rootView.findViewById(R.id.footer_progressBar);
        footer_textView = (TextView) rootView.findViewById(R.id.footer_textView);
        //初始化footerLayout的状态
        //setState(STATE_NORMAL);
    }

    /**
     * 设置footerView的状态
     * @param state
     */
    @Override
    public void setState(int state) {
        if (state == STATE_NORMAL){
            setNormal();
        }else if(state == STATE_LOADING){
            setLoading();
        }else if (state == STATE_LOADFAIL){
            setLoadFail();
        }else if (state == STATE_PAGINGLOADCOMPLETE){
            setPagingLoadComplete();
        }
    }

    /**
     * 设置正在加载状态
     */
    @Override
    public void setLoading() {
        footer_textView.setVisibility(View.VISIBLE);
        footer_progressBar.setVisibility(View.VISIBLE);
        footer_textView.setText(R.string.loadMore_loading_text);
        LayoutParams lp = (LayoutParams)rootView.getLayoutParams();
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        rootView.setLayoutParams(lp);
    }

    /**
     * 设置加载失败状态
     */
    @Override
    public void setLoadFail() {
        footer_progressBar.setVisibility(View.GONE);
        footer_textView.setText(R.string.loadMore_fail_text);
        LayoutParams lp = (LayoutParams)rootView.getLayoutParams();
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        rootView.setLayoutParams(lp);
    }


    /**
     * 设置分页加载完成状态
     */
    @Override
    public void setPagingLoadComplete() {
        LayoutParams lp = (LayoutParams)rootView.getLayoutParams();
        lp.height = 0;
        rootView.setLayoutParams(lp);
    }

    /**
     * 设置正常状态
     */
    @Override
    public void setNormal() {
        LayoutParams lp = (LayoutParams)rootView.getLayoutParams();
        lp.height = 0;
        rootView.setLayoutParams(lp);
       /* footer_progressBar.setVisibility(View.GONE);
        footer_textView.setVisibility(View.GONE);*/
    }

}
