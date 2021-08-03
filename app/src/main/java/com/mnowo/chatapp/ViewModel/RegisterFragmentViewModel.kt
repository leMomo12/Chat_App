package com.mnowo.chatapp.ViewModel

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
class RegisterFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private var _status: MutableLiveData<Event<Resource<String>>> = MutableLiveData()

    val status : LiveData<Event<Resource<String>>>
        get() = _status

    fun registerWithEmailAndPassword(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.registerWithEmailAndPassword(email, password)
    }

    fun checkInputs(email: String, password: String, username: String) = viewModelScope.launch(Dispatchers.IO) {
        when {
            email.isEmpty() -> {
                _status.postValue(Event(Resource.error("Email empty", "sdaf")))
            }
            password.isEmpty() -> {
                _status.postValue(Event(Resource.error("Password empty", "fsd")))
            }
            username.isEmpty() -> {
                _status.postValue(Event(Resource.error("Username empty", "ffds")))
            }
            else -> {
                _status.postValue(Event(Resource.success("Success")))
            }
        }
    }
}