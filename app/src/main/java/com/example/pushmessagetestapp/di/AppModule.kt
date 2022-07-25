package com.example.pushmessagetestapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Named("Login")
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context) =
        context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)


    private companion object {
        const val LOGIN_PREFERENCES = "LOGIN_SHARED_PREFERENCES"
    }
}