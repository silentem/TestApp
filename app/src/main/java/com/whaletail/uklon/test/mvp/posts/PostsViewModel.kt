package com.whaletail.uklon.test.mvp.posts

import android.arch.lifecycle.MutableLiveData
import com.whaletail.uklon.test.api.PostAPI
import com.whaletail.uklon.test.model.Post
import com.whaletail.uklon.test.network
import com.whaletail.uklon.test.util.BaseViewModel
import com.whaletail.uklon.test.util.State
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val postAPI: PostAPI) : BaseViewModel() {

    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPosts() {
        state.value = State.LOADING
        call(postAPI.getPosts()
                .network()
                .subscribe(
                        { v -> showPostsSuccess(v) },
                        { showPostsError() }))
    }

    private fun showPostsError() {
        state.value = State.LOADED
    }

    private fun showPostsSuccess(posts: List<Post>) {
        state.value = State.LOADED
        postsLiveData.value = posts
    }

}