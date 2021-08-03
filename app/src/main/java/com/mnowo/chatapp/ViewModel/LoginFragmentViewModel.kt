package com.mnowo.chatapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.chatapp.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    fun loginWithEmailAndPassword(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.loginWithEmailAndPassword(email, password)
    }
}