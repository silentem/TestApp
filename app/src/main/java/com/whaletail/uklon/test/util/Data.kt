package com.whaletail.uklon.test.util


enum class DataState { SUCCESS, ERROR }

data class Data<out T> constructor(val dataState: DataState = DataState.SUCCESS, val data: T? = null, val message: String? = null)