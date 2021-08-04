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
import com.mnowo.chatapp.ViewModel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.view.*

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)


        view.btn_login.setOnClickListener {
            view.pb_login.isVisible = true
            view.btn_login.isClickable = false
            beforeLogin()
        }

        view.tv_toRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        // Checks if no field is empty and then logs in the user
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it.getContentIfNotHandled()?.data == "Success") {
                login()
            } else {
                Log.d("login", "Login Failed")
                Snackbar.make(view, R.string.makeSureThatNoFieldIsEmpty, Snackbar.LENGTH_LONG).show()
                view.pb_login.isVisible = false
                view.btn_login.isClickable = true
            }
        })

        // Checks if user logged in successfully and then navigates to the homeFragment
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
            if(it) {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null, navOptions)
            } else {
                Snackbar.make(view, R.string.somethingWentWrong, Snackbar.LENGTH_LONG).show()
                view.pb_login.isVisible = false
                view.btn_login.isClickable = true
            }
        })

        return view
    }

    // Checks if inputs are valid
    private fun beforeLogin() {
        val email = view?.et_loginEmail?.text.toString()
        val password = view?.et_loginPassword?.text.toString()

        viewModel.checkInput(email, password)
    }

    private fun login() {
        val email = view?.et_loginEmail?.text.toString()
        val password = view?.et_loginPassword?.text.toString()

        viewModel.loginWithEmailAndPassword(email, password)
    }


}