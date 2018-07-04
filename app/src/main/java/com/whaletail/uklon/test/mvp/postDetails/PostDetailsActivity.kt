package com.whaletail.uklon.test.mvp.postDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import com.whaletail.uklon.test.startLoading
import com.whaletail.uklon.test.stopLoading
import com.whaletail.uklon.test.util.UklonTestActivity
import kotlinx.android.synthetic.main.activity_post_details.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostDetailsActivity : UklonTestActivity() {
    companion object {

        const val POST_ID: String = "post_id"
        const val USER_ID: String = "user_id"
    }

    @Inject
    lateinit var adapter: CommentsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostDetailsViewModel::class.java)

        viewModel.state.observe(this, Observer {
            when (it) {
                State.COMMENTS_ERROR -> {
                    srl_post_details.stopLoading()
                    toast(getString(R.string.post_details_can_not_load_comments))
                }
                State.COMMENTS_SUCCESS -> {
                    srl_post_details.stopLoading()
                }
                State.USER_ERROR -> {
                    toast(getString(R.string.post_details_can_not_load_user))
                }
                State.LOADING -> {
                    srl_post_details.startLoading()
                }
                State.USER_SUCCESS -> {

                }
            }
        })

        viewModel.commentsLiveData.observe(this, Observer {
            adapter.comments = it ?: emptyList()
        })

        viewModel.userLiveData.observe(this, Observer {
            adapter.user = it
        })

        rv_comments.adapter = adapter
        srl_post_details.setOnRefreshListener { loadData() }
        loadData()
    }

    fun getPostId(): Int = intent.getIntExtra(POST_ID, 0)

    fun getUserId(): Int = intent.getIntExtra(USER_ID, 0)


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    private fun loadData() {
        viewModel.getComments()
        viewModel.getUser()
    }

}
