package com.whaletail.uklon.test.dagger.modules

import com.whaletail.uklon.test.mvp.postDetails.PostDetailsActivity
import com.whaletail.uklon.test.mvp.postDetails.PostDetailsActivityModule
import com.whaletail.uklon.test.mvp.posts.PostsActivity
import com.whaletail.uklon.test.mvp.posts.PostsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author whaletail on 20.03.18.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(PostDetailsActivityModule::class))
    abstract fun bindPostDetailsActivity(): PostDetailsActivity

    @ContributesAndroidInjector(modules = arrayOf(PostsActivityModule::class))
    abstract fun bindPostsActivity(): PostsActivity
}