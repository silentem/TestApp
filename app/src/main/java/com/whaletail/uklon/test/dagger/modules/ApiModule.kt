package com.whaletail.uklon.test.dagger.modules

import com.whaletail.uklon.test.api.CommentsAPI
import com.whaletail.uklon.test.api.PostAPI
import com.whaletail.uklon.test.api.UsersAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideUserCall(retrofit: Retrofit): UsersAPI = retrofit.create(UsersAPI::class.java)

    @Provides
    fun provideCommentsCall(retrofit: Retrofit): CommentsAPI = retrofit.create(CommentsAPI::class.java)

    @Provides
    fun providePostsCall(retrofit: Retrofit): PostAPI = retrofit.create(PostAPI::class.java)
}