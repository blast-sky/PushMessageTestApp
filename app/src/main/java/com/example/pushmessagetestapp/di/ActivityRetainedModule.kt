package com.example.pushmessagetestapp.di

import com.example.pushmessagetestapp.data.repository.DefaultRepository
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class ActivityRetainedModule {

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    interface ActivityRetainedModuleBinds {

        @ActivityRetainedScoped
        @Binds
        fun bindRepository(impl: DefaultRepository) : Repository
    }
}