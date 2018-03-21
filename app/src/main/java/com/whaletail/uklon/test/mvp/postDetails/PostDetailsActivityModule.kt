package com.whaletail.uklon.test.mvp.postDetails

import com.whaletail.uklon.test.api.CommentsAPI
import com.whaletail.uklon.test.api.UsersAPI
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author whaletail on 20.03.18.
 */
@Module
class PostDetailsActivityModule {
    @Provides
    fun provideAdapter(): CommentsAdapter = CommentsAdapter()

    @Provides
    @Named("user_id")
    fun provideUserId(postDetailsActivity: PostDetailsActivity): Int = postDetailsActivity.getUserId()

    @Provides
    @Named("post_id")
    fun providePostId(postDetailsActivity: PostDetailsActivity): Int = postDetailsActivity.getPostId()

    @Provides
    fun provideActivityView(postDetailsActivity: PostDetailsActivity): PostDetailsView = postDetailsActivity

    @Provides
    fun provideCommentsCall(retrofit: Retrofit, @Named("post_id") postId: Int): Observable<List<Comment>> = retrofit.create(CommentsAPI::class.java).getCommentsByPost(postId)

    @Provides
    fun provideUserCall(retrofit: Retrofit, @Named("user_id") userId: Int): Observable<User> = retrofit.create(UsersAPI::class.java).getUserById(userId)

    @Provides
    fun provideActivityPresenter(view: PostDetailsView,
                                 commentsCall: Observable<List<Comment>>,
                                 userCall: Observable<User>): PostDetailsPresenter = PostDetailsPresenterImpl(view, commentsCall, userCall)

}