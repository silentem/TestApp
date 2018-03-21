package com.whaletail.uklon.test.mvp.postDetails

import android.os.Bundle
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import com.whaletail.uklon.test.util.UklonTestActivity
import kotlinx.android.synthetic.main.activity_post_details.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostDetailsActivity : UklonTestActivity(), PostDetailsView {
    companion object {

        const val POST_ID: String = "post_id"
        const val USER_ID: String = "user_id"
    }

    @Inject
    lateinit var adapter: CommentsAdapter

    @Inject
    lateinit var presenter: PostDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        rv_comments.adapter = adapter
        srl_post_details.setOnRefreshListener { loadData() }
        loadData()
    }

    fun getPostId(): Int = intent.getIntExtra(POST_ID, 0)

    fun getUserId(): Int = intent.getIntExtra(USER_ID, 0)

    override fun showPostCommentsError() {
        toast(getString(R.string.post_details_can_not_load_comments))
    }

    override fun showUserError() {
        toast(getString(R.string.post_details_can_not_load_user))
    }

    override fun showPostComments(comments: List<Comment>) {
        adapter.comments = comments
        srl_post_details.isRefreshing = false
    }

    override fun showUser(user: User) {
        adapter.user = user
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    private fun loadData() {
        srl_post_details.isRefreshing = true
        presenter.getComments()
        presenter.getUser()
    }

}
