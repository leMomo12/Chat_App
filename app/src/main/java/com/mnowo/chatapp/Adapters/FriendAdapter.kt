package com.mnowo.chatapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.R
import kotlinx.android.synthetic.main.custom_friends_row.view.*
import javax.inject.Inject

class FriendAdapter @Inject constructor()
    :ListAdapter<Friend, FriendAdapter.MyViewHolder>(UserDiffCallback()){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_friends_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.tv_friendName.text = currentItem.profileName

        holder.itemView.ib_addFriend.setOnClickListener {

        }
    }

    class UserDiffCallback: DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem == newItem
        }

    }
}