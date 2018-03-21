package com.whaletail.uklon.test.api

import com.whaletail.uklon.test.model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author whaletail on 20.03.18.
 */

interface UsersAPI {

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Observable<User>
}