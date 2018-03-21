package com.whaletail.uklon.test.mvp.posts

import com.whaletail.uklon.test.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author whaletail on 21.03.18.
 */

interface PostsActivityView {
    fun showPosts(posts: List<Post>)
}

interface PostsActivityPresenter {
    fun getPosts()
}

class PostsActivityPresenterImpl @Inject constructor(private val postsActivityView: PostsActivityView,
                                                     private val postsCall: Observable<List<Post>>) : PostsActivityPresenter {

    override fun getPosts() {
        postsCall.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { result ->
                    postsActivityView.showPosts(result)
                }
    }

}