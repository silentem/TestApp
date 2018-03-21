package com.whaletail.uklon.test.mvp.postDetails

import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * @author whaletail on 21.03.18.
 */

interface PostDetailsView {
    fun showPostComments(comments: List<Comment>)
    fun showUser(user: User)
}

interface PostDetailsPresenter {
    fun getComments()
    fun getUser()
}

class PostDetailsPresenterImpl @Inject constructor(private val postDetailsView: PostDetailsView,
                                                   private val commentsCall: Observable<List<Comment>>,
                                                   private val userCall: Observable<User>) : PostDetailsPresenter, AnkoLogger {
    override fun getUser() {
        userCall.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { v -> postDetailsView.showUser(v) }
    }

    override fun getComments() {
        commentsCall.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { v -> postDetailsView.showPostComments(v) }
    }


}