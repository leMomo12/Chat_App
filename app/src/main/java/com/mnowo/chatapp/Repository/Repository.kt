package com.mnowo.chatapp.Repository

interface Repository {

    suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean
}