package com.whaletail.uklon.test.mvp.postDetails

import android.os.Bundle
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import com.whaletail.uklon.test.util.UklonTestActivity
import kotlinx.android.synthetic.main.activity_post_details.*
import javax.inject.Inject

class PostDetailsActivity : UklonTestActivity(), PostDetailsView {

    @Inject
    lateinit var adapter: CommentsAdapter
    @Inject
    lateinit var presenter: PostDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        rv_comments.adapter = adapter
        presenter.getComments()
        presenter.getUser()
    }

    fun getPostId(): Int = intent.getIntExtra("post_id", 0)

    fun getUserId(): Int = intent.getIntExtra("user_id", 0)

    override fun showPostComments(comments: List<Comment>) {
        adapter.comments = comments
    }

    override fun showUser(user: User) {
        adapter.user = user
    }
}
