package com.mnowo.chatapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mnowo.chatapp.R
import com.mnowo.chatapp.ViewModel.RegisterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_register, container, false)

        view.btn_createUser.setOnClickListener {
            view.pb_register.isVisible = true
            view.btn_createUser.isClickable = false
            beforeRegistration()
        }

        view.tv_toLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it.getContentIfNotHandled()?.data == "Success") {
                registerUser()
            } else {
                Log.d("registration", "Failed")
                Snackbar.make(view, R.string.makeSureThatNoFieldIsEmpty, Snackbar.LENGTH_LONG).show()
                view.pb_register.isVisible = false
                view.btn_createUser.isClickable = true
            }
        })

        viewModel.isRegistered.observe(viewLifecycleOwner, Observer {
            if(it) {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.registerFragment, true).build()
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment, null, navOptions)
            } else {
                Snackbar.make(view, R.string.somethingWentWrong, Snackbar.LENGTH_LONG).show()
                view.pb_register.isVisible = false
                view.btn_createUser.isClickable = true
            }
        })

        return view
    }


    // Checks if inputs are valid
    private fun beforeRegistration() {
        val username = view?.et_username?.text.toString()
        val email = view?.et_email?.text.toString()
        val password = view?.et_password?.text.toString()

        viewModel.checkInputs(email, password, username)
    }

    private fun registerUser() {
        val email = view?.et_email?.text.toString()
        val password = view?.et_password?.text.toString()
        val username = view?.et_username?.text.toString()

        viewModel.registerWithEmailAndPassword(email, password, username)
    }
}