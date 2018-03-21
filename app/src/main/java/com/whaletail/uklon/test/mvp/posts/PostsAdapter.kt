package com.whaletail.uklon.test.mvp.posts

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Post
import com.whaletail.uklon.test.mvp.postDetails.PostDetailsActivity
import kotlinx.android.synthetic.main.post_holder_layout.view.*

/**
 * @author whaletail on 21.03.18.
 */

class PostsAdapter(private val postsActivity: PostsActivity) : RecyclerView.Adapter<PostsAdapter.PostHolder>() {

    var posts: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bindPost(posts[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_holder_layout, parent, false))
    }

    inner class PostHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindPost(post: Post) {
            itemView.tv_post_title.text = post.title
            itemView.tv_post_body.text = post.body
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PostDetailsActivity::class.java)
                intent.putExtra("post_id", post.id)
                intent.putExtra("user_id", post.userId)
                itemView.context.startActivity(intent)
                postsActivity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
        }
    }

}