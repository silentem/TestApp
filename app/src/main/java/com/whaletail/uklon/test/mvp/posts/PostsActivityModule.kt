package com.whaletail.uklon.test.mvp.posts

import com.whaletail.uklon.test.api.PostAPI
import com.whaletail.uklon.test.model.Post
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit

/**
 * @author whaletail on 20.03.18.
 */

@Module
class PostsActivityModule {

    @Provides
    fun providePostsAdapter(): PostsAdapter = PostsAdapter()

    @Provides
    fun providePostsActivityView(postsActivity: PostsActivity): PostsActivityView = postsActivity

    @Provides
    fun providePostsCall(retrofit: Retrofit): Observable<List<Post>> = retrofit.create(PostAPI::class.java).getPosts()

    @Provides
    fun providePostsActivityPresenter(postsActivityView: PostsActivityView, postsCall: Observable<List<Post>>): PostsActivityPresenter = PostsActivityPresenterImpl(postsActivityView, postsCall)


}
