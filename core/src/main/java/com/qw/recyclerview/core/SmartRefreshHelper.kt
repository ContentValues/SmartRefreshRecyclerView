package com.qw.recyclerview.core

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by qinwei on 2021/6/29 21:08
 */
class SmartRefreshHelper : SmartRefreshable {
    private lateinit var mSmartRefreshable: SmartRefreshable
    fun inject(smartRefreshable: SmartRefreshable) {
        mSmartRefreshable = smartRefreshable
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<BaseViewHolder>) {
        mSmartRefreshable.setAdapter(adapter)
    }

    override fun getRecyclerView(): RecyclerView {
        return mSmartRefreshable.getRecyclerView()
    }

    override fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        mSmartRefreshable.setLayoutManager(layoutManager)
    }

    override fun setItemAnimator(itemAnimator: RecyclerView.ItemAnimator) {
        mSmartRefreshable.setItemAnimator(itemAnimator)
    }

    override fun setOnRefreshListener(onRefreshListener: OnRefreshListener) {
        mSmartRefreshable.setOnRefreshListener(onRefreshListener)
    }

    override fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        mSmartRefreshable.setOnLoadMoreListener(onLoadMoreListener)
    }

    override fun setRefreshEnable(isEnabled: Boolean) {
        mSmartRefreshable.setRefreshEnable(isEnabled)
    }

    override fun setLoadMoreEnable(isEnabled: Boolean) {
        mSmartRefreshable.setLoadMoreEnable(isEnabled)
    }

    override fun setRefreshing(refreshing: Boolean) {
        mSmartRefreshable.setRefreshing(refreshing)
    }

    override fun setLoadMore(delayed: Int, success: Boolean, noMoreData: Boolean) {
        mSmartRefreshable.setLoadMore(delayed, success, noMoreData)
    }
}