package com.whaletail.uklon.test.mvp.postDetails

import android.arch.lifecycle.ViewModelProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.whaletail.uklon.test.*
import com.whaletail.uklon.test.mvp.updateService.UpdateService
import com.whaletail.uklon.test.util.BaseActivity
import com.whaletail.uklon.test.util.DataState
import com.whaletail.uklon.test.util.State
import io.reactivex.android.schedulers.AndroidSchedulers
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

    lateinit var serviceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)


        startService(Intent(this, UpdateService::class.java))

        serviceConnection = object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                p1 as UpdateService.UpdateServiceBinder
                val service = p1.getService()
                viewModel.observeCommentsUpdates(service.observeUpdates())
            }

        }
        bindService(Intent(this, UpdateService::class.java), serviceConnection, Context.BIND_AUTO_CREATE)

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
                    RegisterState.SUCCESS_FROM_SERVICE -> {
                        toast("updated from service")
                        adapter.comments = it.data ?: emptyList()
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
