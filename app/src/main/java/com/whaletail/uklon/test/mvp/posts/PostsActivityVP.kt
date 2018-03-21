package com.whaletail.uklon.test.mvp.posts

import com.whaletail.uklon.test.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * @author whaletail on 21.03.18.
 */

interface PostsActivityView {
    fun showPosts(posts: List<Post>)
    fun showPostsError()
}

interface PostsActivityPresenter {
    fun getPosts()
}

class PostsActivityPresenterImpl @Inject constructor(private val postsActivityView: PostsActivityView,
                                                     private val postsCall: Observable<List<Post>>) : PostsActivityPresenter, AnkoLogger {

    override fun getPosts() {
        postsCall.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { v -> postsActivityView.showPosts(v) },
                        { postsActivityView.showPostsError() }
                )
    }

}