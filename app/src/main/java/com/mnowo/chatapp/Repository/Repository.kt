package com.mnowo.chatapp.Repository

import com.mnowo.chatapp.Other.Class.Friend
import com.mnowo.chatapp.Other.Class.User

interface Repository {

    // Firebase
    suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean

    suspend fun addUserToFirestore(customId: String, user: User)

    suspend fun searchUser(customId: String) : MutableList<User>

    // Local
    suspend fun addFriend(friend: Friend)

    suspend fun getFriends() : MutableList<Friend>
}