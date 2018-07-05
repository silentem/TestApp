package com.whaletail.uklon.test.mvp.posts

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.whaletail.uklon.test.*
import com.whaletail.uklon.test.util.State
import com.whaletail.uklon.test.util.UklonTestActivity
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity : UklonTestActivity() {
    @Inject
    lateinit var postAdapter: PostsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        withViewModel<PostsViewModel>(viewModelFactory) {
            viewModel = this

            observe(postsLiveData) {
                postAdapter.posts = it ?: emptyList()
            }

            observe(state) {
                when (it) {
                    State.LOADED -> srl_posts.loaded()
                    State.LOADING -> srl_posts.loading()
                }
            }

        }

        rv_posts.adapter = postAdapter
        srl_posts.setOnRefreshListener { loadData() }
        loadData()
    }


    private fun loadData() {
        viewModel.getPosts()
    }

}
