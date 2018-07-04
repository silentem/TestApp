package com.whaletail.uklon.test

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View

fun SwipeRefreshLayout.startLoading() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopLoading() {
    isRefreshing = false
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}