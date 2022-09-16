package com.astrog.chats_list.di

import android.content.Context
import com.astrog.chats_list.data.remote.retrofit.MessagingService
import com.astrog.chats_list.data.repository.AndroidResources
import com.astrog.chats_list.data.repository.DefaultRepository
import com.astrog.chats_list.domain.repository.Repository
import com.astrog.chats_list.domain.repository.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @InstallIn(SingletonComponent::class)
    @Module
    interface Binder {

        @Binds
        fun bindRepository(impl: DefaultRepository): Repository

        @Binds
        fun bindResources(impl: AndroidResources): Resources
    }

    @Singleton
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

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(MessagingService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideMessagingService(retrofit: Retrofit): MessagingService = retrofit
        .create(MessagingService::class.java)

    @Singleton
    @Provides
    fun provideResources(@ApplicationContext context: Context): android.content.res.Resources =
        context.resources
}