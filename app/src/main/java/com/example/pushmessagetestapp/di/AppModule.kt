package com.example.pushmessagetestapp.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.pushmessagetestapp.data.repository.DefaultRepository
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Named("Login")
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideResource(@ApplicationContext context: Context): Resources =
        context.resources


    private companion object {
        const val LOGIN_PREFERENCES = "LOGIN_SHARED_PREFERENCES"
    }


}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AppModuleBinds {

    @ActivityRetainedScoped
    @Binds
    fun bindRepository(impl: DefaultRepository) : Repository
}