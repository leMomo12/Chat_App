package com.mnowo.chatapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.custom_home_row.view.*
import javax.inject.Inject


class HomeRecyclerAdapter @Inject constructor()
    : ListAdapter<Friend, HomeRecyclerAdapter.MyViewHolder>(UserDiffCallback()) {


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_home_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.tv_profileName.text = currentItem.profileName

        Picasso.get()
            .load(R.mipmap.ic_launcher_round)
            .transform(CropCircleTransformation())
            .into(holder.itemView.iv_profilePicture)

        //.transform(CropCircleTransformation())

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