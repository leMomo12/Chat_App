package com.mnowo.chatapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _friendList: MutableLiveData<MutableList<Friend>> = MutableLiveData()

    val friendList: LiveData<MutableList<Friend>>
    get() = _friendList

    fun getFriends() = viewModelScope.launch(Dispatchers.IO) {
        _friendList.postValue(repository.getFriends())
    }

}