package com.mnowo.chatapp.ViewModel

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.Other.Class.User
import com.mnowo.chatapp.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFriendFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _searchResultList: MutableLiveData<MutableList<User>> = MutableLiveData()

    val searchResultList: LiveData<MutableList<User>>
    get() = _searchResultList

    var customId = ""

    fun onFriendAddClicked(friend: Friend) = viewModelScope.launch(Dispatchers.IO) {
        val friend = Friend(0, friend.profileName, friend.profilePicture, customId)

        repository.addFriend(friend)
    }


    fun searchUser(customId: String) = viewModelScope.launch(Dispatchers.IO) {
        _searchResultList.postValue(repository.searchUser(customId))
    }
}