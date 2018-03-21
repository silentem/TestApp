package com.whaletail.uklon.test.api

import com.whaletail.uklon.test.model.Post
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author whaletail on 20.03.18.
 */
interface PostAPI {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>
}