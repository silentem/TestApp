package com.whaletail.uklon.test.mvp.postDetails

import android.arch.lifecycle.MutableLiveData
import com.whaletail.uklon.test.api.CommentsAPI
import com.whaletail.uklon.test.api.UsersAPI
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import com.whaletail.uklon.test.network
import com.whaletail.uklon.test.util.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(private val commentsAPI: CommentsAPI,
                                               private val usersAPI: UsersAPI) : BaseViewModel() {


    val commentsLiveData: MutableLiveData<RawData<List<Comment>, RegisterState>> = MutableLiveData()

    val userLiveData: MutableLiveData<Data<User>> = MutableLiveData()

    fun getUser(id: Int) {
        state.value = State.LOADING
        call(usersAPI.getUserById(id)
                .network()
                .subscribe(
                        { result -> showUserSuccess(result) },
                        { showUserError() }))
    }

    fun getComments(id: Int) {
        call(commentsAPI.getCommentsByPost(id)
                .network()
                .subscribe(
                        { v -> showCommentsSuccess(v) },
                        { showCommentsError() }))
    }

    fun observeCommentsUpdates(commentsObservable: Observable<List<Comment>>?) {

        commentsObservable
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { comments -> showCommentsFromServiceSuccess(comments) }
    }

    private fun showUserSuccess(user: User) {
        userLiveData.value = Data(data = user)
    }

    private fun showUserError() {
        state.value = State.LOADED
        userLiveData.value = Data(dataState = DataState.ERROR)
    }

    private fun showCommentsSuccess(comments: List<Comment>) {
        state.value = State.LOADED
        commentsLiveData.value = RawData(dataState = RegisterState.SUCCESS, data = comments)
    }

    private fun showCommentsFromServiceSuccess(comments: List<Comment>) {
        state.value = State.LOADED
        commentsLiveData.value = RawData(dataState = RegisterState.SUCCESS_FROM_SERVICE, data = comments)
    }

    private fun showCommentsError() {
        state.value = State.LOADED
        commentsLiveData.value = RawData(RegisterState.WRONG_PASSWORD, data = null)
    }


}