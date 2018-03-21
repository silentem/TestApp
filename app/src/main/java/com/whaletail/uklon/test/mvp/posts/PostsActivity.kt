package com.whaletail.uklon.test.mvp.posts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.util.UklonTestActivity
import kotlinx.android.synthetic.main.activity_posts.*

class PostsActivity : UklonTestActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        rv_posts.layoutManager = LinearLayoutManager(this)
    }
}
