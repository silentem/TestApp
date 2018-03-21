package com.whaletail.uklon.test.mvp.posts

import android.os.Bundle
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Post
import com.whaletail.uklon.test.util.UklonTestActivity
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity : UklonTestActivity(), PostsActivityView {
    @Inject
    lateinit var postAdapter: PostsAdapter

    @Inject
    lateinit var presenter: PostsActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        rv_posts.adapter = postAdapter
        srl_posts.setOnRefreshListener { loadData() }
        loadData()
    }

    override fun showPosts(posts: List<Post>) {
        postAdapter.posts = posts
        srl_posts.isRefreshing = false
    }

    private fun loadData() {
        srl_posts.isRefreshing = true
        presenter.getPosts()
    }

}
