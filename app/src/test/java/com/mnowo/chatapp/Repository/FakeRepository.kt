package com.mnowo.chatapp.Repository

import com.mnowo.chatapp.Other.Class.User

class FakeRepository: Repository {


    override suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean {
        return true
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean {
        return true
    }

    override suspend fun addUserToFirestore(customId: String, user: User) {

    }

}