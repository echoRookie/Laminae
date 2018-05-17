package com.example.rookie.laminae.main.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by rookie on 2018/4/26.
 */

public abstract class ScrollListener< T > extends RecyclerView.OnScrollListener{
    //获取recyclerview布局管理器
    private T myManager;
    //适配器的总子布局数
    private int totalItem;
    //当前可见的子布局位置
    private int visibleItem;
    //当前最后一个子布局的位置
    private int lastItem;
    public ScrollListener(T manager){
        myManager = manager;

    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if ( myManager instanceof LinearLayoutManager) {
            totalItem = ((LinearLayoutManager) myManager).getItemCount();
            visibleItem = ((LinearLayoutManager)myManager).getChildCount();
            lastItem = ((LinearLayoutManager)myManager).findLastCompletelyVisibleItemPosition();
            //满足条件调用onLoadMore   法加载
            //当前可见子布局数大于零
            //向下滑动
            //当前可见子布局为适配器中最后一项
            if (visibleItem > 0 && dy > 0 && lastItem >= totalItem - 1) {
                onLoadMore();
            }
        }
        else {
            totalItem = ((GridLayoutManager) myManager).getItemCount();
            visibleItem = ((GridLayoutManager)myManager).getChildCount();
            lastItem = ((GridLayoutManager)myManager).findLastCompletelyVisibleItemPosition();
            //满足条件调用onLoadMore   法加载
            //当前可见子布局数大于零
            //向下滑动
            //当前可见子布局为适配器中最后一项
            if (visibleItem > 0 && dy > 0 && lastItem >= totalItem - 1) {
                onLoadMore();
            }
        }
    }
    //自定义的加载抽象方法
    public abstract void onLoadMore();
}
