package com.mnowo.chatapp.DI

import android.content.Context
import androidx.room.Room
import com.mnowo.chatapp.Database.Firebase.Firebase
import com.mnowo.chatapp.Database.Room.ChatDao
import com.mnowo.chatapp.Database.Room.ChatDatabase
import com.mnowo.chatapp.Other.Constants.Constants.DATABASE_NAME
import com.mnowo.chatapp.Repository.DefaultRepository
import com.mnowo.chatapp.Repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        firebase: Firebase,
        chatDao: ChatDao
    ) = DefaultRepository(firebase, chatDao) as Repository


    @Singleton
    @Provides
    fun provideChatDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ChatDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideChatDao(
        database: ChatDatabase
    ) = database.dao()
}