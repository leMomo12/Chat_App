package com.mnowo.chatapp.Repository

import com.mnowo.chatapp.Database.Firebase
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val firebase: Firebase
) : Repository{

    override suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean = firebase.registerWithEmailAndPassword(email, password)

    override suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean = firebase.loginWithEmailAndPassword(email, password)
}