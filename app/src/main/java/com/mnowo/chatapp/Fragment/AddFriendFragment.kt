package com.mnowo.chatapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnowo.chatapp.Adapters.FriendAdapter
import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.R
import com.mnowo.chatapp.ViewModel.AddFriendFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_friend.view.*

@AndroidEntryPoint
class AddFriendFragment : Fragment() {

    private val viewModel: AddFriendFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_add_friend, container, false)

        view.btn_search.setOnClickListener {
            val customId = view.et_searchFriends.text.toString()
            viewModel.customId = customId
            viewModel.searchUser(customId)
        }

        val adapter = FriendAdapter(FriendAdapter.AddFriendListener { id ->
            viewModel.onFriendAddClicked(id)
        })
        val recyclerview = view.rv_friends
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.searchResultList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return view
    }


}