package com.credithc.loadmoredemo.loadmorelistview;

/**
 *  Created by zgj on 2015/10/12.
 *  加载更多的尾布局的控制器
 */
public interface LmFooterHandler {
    //正常状态
    int STATE_NORMAL = 1;
    //加载中
    int STATE_LOADING = 2;
    //加载失败
    int STATE_LOADFAIL = 3;
    //分页加载完成
    int STATE_PAGINGLOADCOMPLETE = 4;

    /**
     * 设置状态
     * @param state
     */
    void setState(int state);

    /**
     * 设置成正常状态
     */
    void setNormal();

    /**
     * 设置成正在加载的状态
     */
    void setLoading();

    /**
     * 设置加载失败
     */
    void setLoadFail();

    /**
     * 设置分页加载完成
      */
    void setPagingLoadComplete();
}
