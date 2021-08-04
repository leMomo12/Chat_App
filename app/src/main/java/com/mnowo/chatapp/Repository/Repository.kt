package com.mnowo.chatapp.Repository

import com.mnowo.chatapp.Other.Class.User

interface Repository {

    suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun addUserToFirestore(customId: String, user: User)
}