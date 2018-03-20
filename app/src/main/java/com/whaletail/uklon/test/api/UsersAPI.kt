package com.whaletail.uklon.test.api

import com.whaletail.uklon.test.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author whaletail on 20.03.18.
 */

interface UsersAPI {

    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/users/{id}")
    fun getUsers(@Path("id") id: Int): Call<List<User>>
}