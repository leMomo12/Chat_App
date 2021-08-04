package com.mnowo.chatapp.ViewModel

import androidx.lifecycle.ViewModel
import com.mnowo.chatapp.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


}