package com.mnowo.chatapp.Repository

import com.mnowo.chatapp.Database.Firebase.Firebase
import com.mnowo.chatapp.Database.Room.ChatDao
import com.mnowo.chatapp.Other.Class.User
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val firebase: Firebase,
    private val chatDao: ChatDao
) : Repository{

    override suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean = firebase.registerWithEmailAndPassword(email, password)

    override suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean = firebase.loginWithEmailAndPassword(email, password)

    override suspend fun addUserToFirestore(customId: String, user: User) = firebase.addUserToFirestore(customId, user)
}