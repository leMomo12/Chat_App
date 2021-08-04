package com.mnowo.chatapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mnowo.chatapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFriendFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_add_friend, container, false)

        return view
    }


}