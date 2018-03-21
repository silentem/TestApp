package com.whaletail.uklon.test.api

import com.whaletail.uklon.test.model.Comment
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author whaletail on 20.03.18.
 */

interface CommentsAPI {

    @GET("/comments")
    fun getComments(): Observable<List<Comment>>

    @GET("post/{id}/comments")
    fun getCommentsByPost(@Path("id") id: Int): Observable<List<Comment>>

}