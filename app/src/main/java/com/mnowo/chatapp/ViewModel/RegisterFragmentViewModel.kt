package com.mnowo.chatapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.chatapp.Other.Class.User

import com.mnowo.chatapp.Other.Util.Event
import com.mnowo.chatapp.Repository.Repository
import com.mnowo.schoolmanager.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private var _status: MutableLiveData<Event<Resource<String>>> = MutableLiveData()

    val status : LiveData<Event<Resource<String>>>
        get() = _status

    private var _isRegistered: MutableLiveData<Boolean> = MutableLiveData()

    val isRegistered: LiveData<Boolean>
        get() = _isRegistered

    fun registerWithEmailAndPassword(email: String, password: String, username: String) = viewModelScope.launch(Dispatchers.IO) {
        addUserToFirestore(username)
        _isRegistered.postValue(repository.registerWithEmailAndPassword(email, password))
    }

    private fun addUserToFirestore(username: String) = viewModelScope.launch {
        val chars = "0123456789"
        var customId = ""

        for (i in 0..6) {
            customId += chars[Random.nextInt(0, chars.length)]
        }

        val user = User(username, "android_launcher", customId,"User")
        repository.addUserToFirestore(customId, user)

        if(customId.length == 6) {
            _status.postValue(Event(Resource.success("CustomId has 6 letters")))
        } else {
            _status.postValue(Event(Resource.error("Something went wrong", null)))
        }
    }


    fun checkInputs(email: String, password: String, username: String) = viewModelScope.launch(Dispatchers.IO) {
        when {
            email.isEmpty() -> {
                _status.postValue(Event(Resource.error("Email empty", null)))
            }
            password.isEmpty() -> {
                _status.postValue(Event(Resource.error("Password empty", null)))
            }
            username.isEmpty() -> {
                _status.postValue(Event(Resource.error("Username empty", null)))
            }
            password.length < 7 || password.length > 7 -> {
                _status.postValue(Event(Resource.error("Password is too short", null)))
            }
            else -> {
                _status.postValue(Event(Resource.success("Success")))
            }
        }
    }
}