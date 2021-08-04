package com.mnowo.chatapp.Database.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnowo.chatapp.Other.Class.Friend

@Database(
    entities = [Friend::class],
    version = 1
)
abstract class ChatDatabase : RoomDatabase(){

    abstract fun dao(): ChatDao
}