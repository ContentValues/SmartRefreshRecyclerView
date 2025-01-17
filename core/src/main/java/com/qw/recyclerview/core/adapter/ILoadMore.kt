package com.qw.recyclerview.core.adapter

import com.qw.recyclerview.core.footer.State

interface ILoadMore {
    /**
     * 是否可加载更多
     */
    fun canLoadMore(): Boolean

    /**
     * 通知ui更新
     */
    fun notifyFooterDataSetChanged(state: State)

    /**
     * 设置是否显示footer
     */
    fun setShowLoadMoreFooter(show: Boolean)
}