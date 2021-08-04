package com.mnowo.chatapp.Database.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mnowo.chatapp.Other.Class.Friend

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFriend(friend: Friend)
}