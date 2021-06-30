package com.qw.recyclerview.swiperefresh

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.qw.recyclerview.core.*

/**
 * Created by qinwei on 2021/6/29 21:44
 */
class SwipeRefreshRecyclerView(private val mRecyclerView: RecyclerView, private val mSwipeRefreshLayout: SwipeRefreshLayout) : SmartRefreshable {
    private var mRefreshEnable: Boolean = false
    private var mLoadMoreEnable: Boolean = false
    private var onRefreshListener: OnRefreshListener? = null
    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var state = SmartRefreshable.REFRESH_IDLE

    init {
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!mLoadMoreEnable) {
                    return
                }
                if (state != SmartRefreshable.REFRESH_IDLE) {
                    return
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && checkedIsNeedLoadMore()) {
                    state = SmartRefreshable.REFRESH_UP
                    onLoadMoreListener?.onLoadMore()
                }
            }
        })
        mSwipeRefreshLayout.setOnRefreshListener {
            state = SmartRefreshable.REFRESH_PULL
            onRefreshListener?.onRefresh()
        }
        mSwipeRefreshLayout.isEnabled = mRefreshEnable
    }

    private fun markIdle() {
        state = SmartRefreshable.REFRESH_IDLE
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        mRecyclerView.adapter = adapter
    }

    private fun checkedIsNeedLoadMore(): Boolean {
        var lastVisiblePosition = 0
        val layoutManager: RecyclerView.LayoutManager = mRecyclerView.layoutManager!!
        if (layoutManager is LinearLayoutManager) {
            lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val sdlm = layoutManager
            lastVisiblePosition = sdlm.findLastCompletelyVisibleItemPositions(null)[sdlm.findLastCompletelyVisibleItemPositions(null).size - 1]
        }
        return mRecyclerView.adapter!!.itemCount - lastVisiblePosition <= 5
    }

    override fun getRecyclerView(): RecyclerView {
        return mRecyclerView
    }

    override fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        mRecyclerView.layoutManager = layoutManager
    }

    override fun setItemAnimator(itemAnimator: RecyclerView.ItemAnimator) {
        mRecyclerView.itemAnimator = itemAnimator
    }

    override fun setOnRefreshListener(onRefreshListener: OnRefreshListener) {
        this.onRefreshListener = onRefreshListener
    }

    override fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    override fun setRefreshEnable(isEnabled: Boolean) {
        mRefreshEnable = isEnabled
        mSwipeRefreshLayout.isEnabled = isEnabled
    }

    override fun setLoadMoreEnable(isEnabled: Boolean) {
        mLoadMoreEnable = isEnabled
    }

    override fun setRefreshing(refreshing: Boolean) {
        mSwipeRefreshLayout.isRefreshing = refreshing
        if (!refreshing) {
            markIdle()
        }
    }

    override fun setLoadMore(delayed: Int, success: Boolean, noMoreData: Boolean) {
        markIdle()
    }
}