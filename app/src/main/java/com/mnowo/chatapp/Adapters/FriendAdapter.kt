package com.mnowo.chatapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.Other.Class.User
import com.mnowo.chatapp.R
import kotlinx.android.synthetic.main.custom_friends_row.view.*
import javax.inject.Inject

class FriendAdapter @Inject constructor(
    val clickListener: AddFriendListener
)
    :ListAdapter<User, FriendAdapter.MyViewHolder>(UserDiffCallback()){


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_friends_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.tv_friendName.text = currentItem.profileName

        holder.itemView.ib_addFriend.setOnClickListener {
            val friend = Friend(0, currentItem.profileName, currentItem.profilePicture, "0000")

            clickListener.onClick(friend)
        }
    }

    class AddFriendListener(val clickListener: (friend: Friend) -> Unit) {
        fun onClick(friend: Friend) = clickListener(friend)
    }

    class UserDiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.customId == newItem.customId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
}