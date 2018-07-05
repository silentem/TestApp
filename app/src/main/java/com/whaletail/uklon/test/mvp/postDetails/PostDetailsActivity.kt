package com.whaletail.uklon.test.mvp.postDetails

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.whaletail.uklon.test.*
import com.whaletail.uklon.test.util.DataState
import com.whaletail.uklon.test.util.State
import com.whaletail.uklon.test.util.BaseActivity
import kotlinx.android.synthetic.main.activity_post_details.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostDetailsActivity : BaseActivity() {
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

        withViewModel<PostDetailsViewModel>(viewModelFactory) {
            viewModel = this
            observe(state) {
                when (it) {
                    State.LOADED -> {
                        srl_post_details.loaded()
                    }
                    State.LOADING -> {
                        srl_post_details.loading()
                    }
                }
            }
            observe(commentsLiveData) {
                when (it?.dataState) {
                    RegisterState.SUCCESS -> {
                        adapter.comments = it.data ?: emptyList()
                    }
                    RegisterState.ERROR -> {
                        toast(getString(R.string.post_details_can_not_load_comments))
                    }
                    RegisterState.WRONG_PASSWORD -> {

                    }
                    RegisterState.WRONG_EMAIL -> {

                    }
                }
            }
            observe(userLiveData) {
                when (it?.dataState) {
                    DataState.SUCCESS -> {
                        adapter.user = it.data
                    }
                    DataState.ERROR -> {
                        toast("Error getting user")
                    }
                }
            }
        }

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
        viewModel.getComments(getPostId())
        viewModel.getUser(getUserId())
    }

}
