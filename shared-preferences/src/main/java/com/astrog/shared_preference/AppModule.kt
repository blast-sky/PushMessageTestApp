package com.astrog.shared_preference

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

annotation class LoginSharedPreferences

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    @LoginSharedPreferences
    fun provideLoginSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)

    private companion object {
        const val LOGIN_PREFERENCES = "LOGIN_SHARED_PREFERENCES"
    }
}