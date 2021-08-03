package com.mnowo.chatapp.DI

import com.mnowo.chatapp.Database.Firebase
import com.mnowo.chatapp.Repository.DefaultRepository
import com.mnowo.chatapp.Repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        firebase: Firebase
    ) = DefaultRepository(firebase) as Repository


}