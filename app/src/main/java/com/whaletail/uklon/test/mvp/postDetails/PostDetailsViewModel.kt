package com.whaletail.uklon.test.mvp.postDetails

import android.arch.lifecycle.MutableLiveData
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import com.whaletail.uklon.test.util.BaseViewModel
import com.whaletail.uklon.test.util.Data
import com.whaletail.uklon.test.util.DataState
import com.whaletail.uklon.test.util.State
import io.reactivex.Observable
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(private val commentsCall: Observable<List<Comment>>,
                                               private val userCall: Observable<User>) : BaseViewModel() {


    val commentsLiveData: MutableLiveData<Data<List<Comment>>> = MutableLiveData()

    val userLiveData: MutableLiveData<User> = MutableLiveData()

    fun getUser() {
        state.value = State.LOADING
        compositeDisposable.add(
                call(userCall).subscribe(
                        { result -> showUserSuccess(result) },
                        { showUserError() }))
    }

    fun getComments() {
        compositeDisposable.add(
                call(commentsCall).subscribe(
                        { v -> showCommentsSuccess(v) },
                        { showCommentsError() }))
    }

    private fun showUserSuccess(user: User) {
        userLiveData.value = user
    }

    private fun showUserError() {
        state.value = State.LOADED
    }

    private fun showCommentsSuccess(comments: List<Comment>) {
        state.value = State.LOADED
        commentsLiveData.value = Data(data = comments)
    }

    private fun showCommentsError() {
        state.value = State.LOADED
        commentsLiveData.value = Data(DataState.ERROR)
    }


}