package com.whaletail.uklon.test.mvp.updateService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.whaletail.uklon.test.api.CommentsAPI
import com.whaletail.uklon.test.model.Comment
import dagger.android.DaggerService
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UpdateService : DaggerService() {


    var observableEmitter: ObservableEmitter<List<Comment>>? = null
    var observable: Observable<List<Comment>>? = null

    @Inject
    lateinit var commentsAPI: CommentsAPI

    inner class UpdateServiceBinder : Binder() {
        fun getService() = this@UpdateService
    }

    private val binder: Binder = UpdateServiceBinder()

    override fun onBind(p0: Intent?): IBinder? = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        toast("Service started!")
        return Service.START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        commentsAPI.getComments()
                .delay(5000, TimeUnit.MILLISECONDS)
                .repeat(10)
                .subscribeOn(Schedulers.io())
                .map {
                    observableEmitter?.onNext(it)
                    it
                }
                .subscribe()
    }

    fun observeUpdates(): Observable<List<Comment>>? {
        if (observable == null) {
            observable = Observable.create { e ->
                observableEmitter = e
            }

            observable = observable?.share()
        }
        return observable
    }

}
