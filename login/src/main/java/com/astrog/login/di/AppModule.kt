package com.astrog.login.di

import com.astrog.login.data.AndroidResources
import com.astrog.login.data.DefaultRepository
import com.astrog.login.domain.repository.Repository
import com.astrog.login.domain.repository.Resources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class AppModuleBinds {

        @Binds
        abstract fun bindsResources(impl: AndroidResources): Resources

        @Binds
        abstract fun bindsRepository(impl: DefaultRepository): Repository
    }
}