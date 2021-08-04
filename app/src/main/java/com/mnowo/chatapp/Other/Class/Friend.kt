package com.mnowo.chatapp.Other.Class

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mnowo.chatapp.Other.Constants.Constants.FRIEND_TABLE

@Entity(tableName = FRIEND_TABLE)
data class Friend(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var profileName: String,
    var profilePicture: String

)