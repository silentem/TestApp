package com.whaletail.uklon.test.mvp.postDetails

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Comment

/**
 * @author whaletail on 21.03.18.
 */
class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    var comments: List<Comment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CommentViewHolder = CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_holder_layout, parent, false))

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder,
                                  position: Int) = holder.bindComment(comments[position])

    inner class CommentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindComment(comment: Comment) {

        }
    }
}