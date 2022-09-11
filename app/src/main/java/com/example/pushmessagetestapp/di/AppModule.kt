package com.example.pushmessagetestapp.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.pushmessagetestapp.data.remote.retrofit.MessagingService
import com.example.pushmessagetestapp.data.repository.AndroidResources
import com.example.pushmessagetestapp.data.repository.DefaultRepository
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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

        @Singleton
        @Binds
        fun bindRepository(impl: DefaultRepository): Repository
    }
}