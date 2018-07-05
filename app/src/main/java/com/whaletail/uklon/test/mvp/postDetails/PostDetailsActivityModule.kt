package com.whaletail.uklon.test.mvp.postDetails

import com.whaletail.uklon.test.api.CommentsAPI
import com.whaletail.uklon.test.api.UsersAPI
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author whaletail on 20.03.18.
 */
@Module
class PostDetailsActivityModule {
    @Provides
    fun provideAdapter(): CommentsAdapter = CommentsAdapter()

}