package com.whaletail.uklon.test.mvp.posts

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
import com.whaletail.uklon.test.util.State
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_posts.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PostsActivity : BaseActivity() {
    @Inject
    lateinit var postAdapter: PostsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: PostsViewModel


    lateinit var serviceConnection: ServiceConnection

    private var bound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)


        serviceConnection = object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
                bound = false
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                bound = true
                p1 as UpdateService.UpdateServiceBinder
                val service = p1.getService()
                service.observeUpdates()
                        ?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe { comments -> if (bound) toast("updated from service started from other activity") }
            }

        }


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


    override fun onStart() {
        super.onStart()
        bindService(Intent(this, UpdateService::class.java), serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        bound = false
        stopService(Intent(this, UpdateService::class.java))
        unbindService(serviceConnection)
    }

    private fun loadData() {
        viewModel.getPosts()
    }

}
