package com.mnowo.chatapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.chatapp.Other.Util.Event
import com.mnowo.chatapp.Repository.Repository
import com.mnowo.schoolmanager.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private var _status: MutableLiveData<Event<Resource<String>>> = MutableLiveData()

    val status: LiveData<Event<Resource<String>>>
    get() = _status


    private var _isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    val isLoggedIn: LiveData<Boolean>
    get() = _isLoggedIn

    fun loginWithEmailAndPassword(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        _isLoggedIn.postValue(repository.loginWithEmailAndPassword(email, password))
    }

    fun checkInput(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        when {
            email.isEmpty() -> {
                _status.postValue(Event(Resource.error("Empty Email", null)))
            }
            password.isEmpty() -> {
                _status.postValue(Event(Resource.error("Empty Password", null)))
            }
            else -> {
                _status.postValue(Event(Resource.success("Success")))
            }
        }
    }
}