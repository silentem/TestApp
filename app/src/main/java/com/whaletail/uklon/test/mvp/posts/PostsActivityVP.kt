package com.whaletail.uklon.test.mvp.posts

import com.whaletail.uklon.test.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * @author whaletail on 21.03.18.
 */

interface PostsActivityView {
    fun showPosts(posts: List<Post>)
    fun onCancel()
}

interface PostsActivityPresenter {
    fun getPosts()
    fun cancel()
}

class PostsActivityPresenterImpl @Inject constructor(val postsActivityView: PostsActivityView,
                                                     private val postsCall: Call<List<Post>>) : PostsActivityPresenter {

    override fun getPosts() {
        postsCall.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                if (response?.isSuccessful == true) {
                    postsActivityView.showPosts(response.body() ?: emptyList())
                }
            }
        })
    }

    override fun cancel() {
        if (!postsCall.isCanceled) {
            postsCall.cancel()
        }
    }

}