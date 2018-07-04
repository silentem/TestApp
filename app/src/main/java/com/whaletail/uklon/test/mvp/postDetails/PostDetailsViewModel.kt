package com.whaletail.uklon.test.mvp.postDetails

import android.arch.lifecycle.MutableLiveData
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import com.whaletail.uklon.test.util.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(private val commentsCall: Observable<List<Comment>>,
                                               private val userCall: Observable<User>) : BaseViewModel() {


    val commentsLiveData: MutableLiveData<List<Comment>> = MutableLiveData()

    val userLiveData: MutableLiveData<User> = MutableLiveData()

    val state: MutableLiveData<State> = MutableLiveData()

    fun getUser() {
        state.value = State.LOADING
        call(userCall).subscribe(
                { result -> showUserSuccess(result) },
                { showUserError() }
        )
    }

    fun getComments() {
        call(commentsCall).subscribe(
                { v -> showCommentsSuccess(v) },
                { showCommentsError() }
        )
    }

    private fun showUserSuccess(user: User) {
        userLiveData.value = user
    }

    private fun showUserError() {
        state.value = State.USER_ERROR
    }

    private fun showCommentsSuccess(comments: List<Comment>) {
        state.value = State.COMMENTS_SUCCESS
        commentsLiveData.value = comments
    }

    private fun showCommentsError() {
        state.value = State.COMMENTS_ERROR
    }


}