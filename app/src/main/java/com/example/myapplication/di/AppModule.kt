package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): Database{
        return Room.databaseBuilder(app, Database::class.java,"Database")
            .build()
    }



}