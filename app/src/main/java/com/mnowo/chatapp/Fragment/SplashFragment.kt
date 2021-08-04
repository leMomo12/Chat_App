package com.mnowo.chatapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mnowo.chatapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        lifecycleScope.launch {
            if(FirebaseAuth.getInstance().currentUser != null) {
                delay(1000)
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment, null, navOptions)
            } else {
                delay(1000)
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
                findNavController().navigate(R.id.action_splashFragment_to_registerFragment, null, navOptions)
            }
        }

        return view
    }


}