package com.whaletail.uklon.test.mvp.postDetails

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whaletail.uklon.test.R
import com.whaletail.uklon.test.model.Comment
import com.whaletail.uklon.test.model.User
import kotlinx.android.synthetic.main.comment_holder_layout.view.*
import kotlinx.android.synthetic.main.user_view_layout.view.*

/**
 * @author whaletail on 21.03.18.
 */
class CommentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val OFFSET: Int = 1

    var comments: List<Comment> = emptyList()
        set(user) {
            field = user
            notifyDataSetChanged()
        }

    var user: User? = null
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                0 -> UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_view_layout, parent, false))
                else -> CommentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_holder_layout, parent, false))
            }

    override fun getItemCount(): Int = comments.size + OFFSET

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) =
            when (position) {
                0 -> (holder as UserViewHolder).bindUser(user)
                else -> (holder as CommentViewHolder).bindComment(comments[position - OFFSET])
            }

    override fun getItemViewType(position: Int): Int = position

    inner class CommentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindComment(comment: Comment) {
            itemView.tv_comment_body.text = comment.body
            itemView.tv_comment_email.text = comment.email
            itemView.tv_comment_name.text = comment.name
        }
    }

    inner class UserViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindUser(user: User?) {
            itemView.tv_user_name.text = user?.name
            itemView.tv_user_email.text = user?.email
            val address = user?.address
            val addressText =
                    if (!address?.city.isNullOrEmpty() && !address?.street.isNullOrEmpty() && !address?.city.isNullOrEmpty())
                        "${address?.city}, ${address?.street}, ${address?.suite}"
                    else ""
            itemView.tv_user_address.text = addressText
            itemView.tv_user_zip_code.text = address?.zipcode
            itemView.tv_user_phone.text = user?.phone
        }
    }
}