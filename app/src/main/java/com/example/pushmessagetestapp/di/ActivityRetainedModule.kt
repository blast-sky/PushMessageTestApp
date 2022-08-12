package com.example.pushmessagetestapp.di

import com.example.pushmessagetestapp.data.remote.retrofit.MessagingService
import com.example.pushmessagetestapp.data.repository.DefaultRepository
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
class ActivityRetainedModule {

    @ActivityRetainedScoped
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val request = original
                .newBuilder()
                .addHeader("Authorization", MessagingService.KEY)
                .build()

            return@addInterceptor chain.proceed(request)
        }
        .build()

    @ActivityRetainedScoped
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(MessagingService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @ActivityRetainedScoped
    @Provides
    fun provideMessagingService(retrofit: Retrofit): MessagingService = retrofit
        .create(MessagingService::class.java)

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    interface ActivityRetainedModuleBinds {

        @ActivityRetainedScoped
        @Binds
        fun bindRepository(impl: DefaultRepository): Repository
    }
}