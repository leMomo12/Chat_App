package com.mnowo.chatapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.mnowo.chatapp.Adapters.HomeRecyclerAdapter
import com.mnowo.chatapp.R
import com.mnowo.chatapp.ViewModel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel.getFriends()

        val adapter = HomeRecyclerAdapter()
        val recyclerview = view.rv_home
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.friendList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        view.btn_logout.setOnClickListener {
            logout()
        }

        view.fab_addFriend.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFriendFragment)
        }





        return view
    }

    private fun logout() {
        com.google.firebase.ktx.Firebase.auth.signOut()

        val navOptions = NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment, null, navOptions)
    }


}