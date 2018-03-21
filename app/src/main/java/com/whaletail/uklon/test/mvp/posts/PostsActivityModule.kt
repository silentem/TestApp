package com.whaletail.uklon.test.mvp.posts

import dagger.Module
import dagger.Provides

/**
 * @author whaletail on 20.03.18.
 */

@Module
class PostsActivityModule {

    @Provides
    fun providePostsAdapter(postsActivity: PostsActivity) = PostsAdapter()

}
