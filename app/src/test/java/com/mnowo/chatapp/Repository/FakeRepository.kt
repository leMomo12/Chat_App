package com.mnowo.chatapp.Repository

class FakeRepository: Repository {


    override suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean {
        return true
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean {
        return true
    }
}