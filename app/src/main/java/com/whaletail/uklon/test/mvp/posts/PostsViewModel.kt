package com.whaletail.uklon.test.mvp.posts

import android.arch.lifecycle.MutableLiveData
import com.whaletail.uklon.test.model.Post
import com.whaletail.uklon.test.util.BaseViewModel
import com.whaletail.uklon.test.util.State
import io.reactivex.Observable
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val postsCall: Observable<List<Post>>) : BaseViewModel() {

    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPosts() {
        state.value = State.LOADING
        call(network(postsCall)
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