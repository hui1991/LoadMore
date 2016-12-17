package com.credithc.loadmoredemo.loadmorelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by zgj on 2015/10/10-9:57
 * 实现了加载更多的ListView
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    //private boolean isPagingLoadComplete;
    //是否加载失败
    //private boolean isLoadFail;
    //是否正在加载更多
    private boolean isLoading;
    //是否需要加载更多
    private boolean isNeedLoadMore;
    //加载更多的监听接口
    private LoadMoreListener loadMoreListener;
    //尾布局
    private FooterLayout mFooterView;
    //上一次点击事件Y轴的坐标
    // private float mLastY;
    //item的数量
    //private int mTotalItemCount;

    public LoadMoreListView(Context context) {
       this(context,null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化试图
     *
     * @param context
     */
    private void initView(Context context) {
        mFooterView = new FooterLayout(context);
        addFooterView(mFooterView);
        //设置滚动的监听
        setOnScrollListener(this);

    }



    /**
     * 加载更多
     */
    private void setLoading() {
        //设置正在加载中
        isLoading = true;
        //设置footerLayout的状态为正在加载中
        mFooterView.setState(FooterLayout.STATE_LOADING);
        //调用加载更多监听接口的加载更多的方法
        loadMoreListener.loadMore();
    }

    /**
     * 设置是否需要加载更多
     * @param isNeedLoadMore
     */
    public void setIsNeedLoadMore(boolean isNeedLoadMore) {
        this.isNeedLoadMore = isNeedLoadMore;
        mFooterView.setState(FooterLayout.STATE_NORMAL);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    //滚动监听
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //mTotalItemCount = totalItemCount;
        if ( isNeedLoadMore && (getLastVisiblePosition() == totalItemCount - 1) && !isLoading) {
            if (loadMoreListener != null) {
                setLoading();
            }
        }

    }

    /**
     *  加载成功后调用
     */
    public void LoadMoreSuccess(){
            isLoading = false;
            mFooterView.setState(FooterLayout.STATE_NORMAL);
            //把点击事件设成空
            mFooterView.setOnClickListener(null);
    }

    /**
     * 加载失败时调用
     */
    public void LoadMoreFail(){
        //isLoading = false;
        //isLoadFail = true;
        mFooterView.setState(FooterLayout.STATE_LOADFAIL);
        mFooterView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadMoreListener != null) {
                    //isLoadFail = false;
                    setLoading();
                }
            }
        });
    }
    /**
     *  分页加载完成后调用
     */
    public void pagingLoadComplete(){
        isNeedLoadMore=false;
        mFooterView.setState(FooterLayout.STATE_PAGINGLOADCOMPLETE);
    }

    /**
     * 加载更多的监听接口
     */
    public interface LoadMoreListener {
        void loadMore();
    }
    /**
     * 加载更多监听接口的set方法
     * @param loadMoreListener
     */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }





    /* *
      * 是否刷新成功
      * @return
      */
//    private boolean isLoadMoreSucceed() {
//          int count = getAdapter().getCount();
//          Log.e("TAG","count"+count);
//          Log.e("TAG", "mTotalItemCount" + mTotalItemCount);
//          return getAdapter().getCount()!=mTotalItemCount;
//   }
    /*        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.height=0;
        mFooterView.setLayoutParams(layoutParams);*/

       /* getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFooterViewHeight=mFooterView.getMeasuredHeight();
                Log.e("TAG","mFooterViewHeight = "+mFooterViewHeight);
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN){
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });*/
    /*
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceY = ev.getRawY() - mLastY;
                //加载失败时调用
                if (isLoadFail && distanceY < (-100) && (getLastVisiblePosition() == mTotalItemCount - 1) ){
                    if (loadMoreListener != null) {
                        isLoadFail = false;
                        setLoading();
                    }
                }
                if (isPagingLoadComplete){
                    mFooterView.setState(getLastVisiblePosition()>mTotalItemCount - 3 ?
                            FooterLayout.STATE_PAGINGLOADCOMPLETE : FooterLayout.STATE_NORMAL);
                    //Log.e("TAG","getLastVisiblePosition = "+getLastVisiblePosition()+" mTotalItemCount = "+mTotalItemCount);
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastY = ev.getRawY();
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
*/
}