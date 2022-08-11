package com.example.pushmessagetestapp.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.pushmessagetestapp.data.repository.AndroidResources
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    @Named("Login")
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideResource(@ApplicationContext context: Context): Resources =
        context.resources


    private companion object {
        const val LOGIN_PREFERENCES = "LOGIN_SHARED_PREFERENCES"
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleBinds {

        @Singleton
        @Binds
        fun bindResources(impl: AndroidResources): com.example.pushmessagetestapp.domain.repository.Resources
    }

}