package com.example.pushmessagetestapp.data

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class SharedPreferencesUserUtil @Inject constructor(
    @Named("Login") private val sharedPreferences: SharedPreferences
) {

    var messageToken
        get() = sharedPreferences.getString(TOKEN, "")!!
        set(value) = sharedPreferences.edit { putString(TOKEN, value) }

    var isLogin
        get() = sharedPreferences.getBoolean(IS_LOGIN, false)
        private set(value) = sharedPreferences.edit { putBoolean(IS_LOGIN, value) }

    var name
        get() = sharedPreferences.getString(NAME, "")!!
        private set(value) = sharedPreferences.edit { putString(NAME, value) }

    fun register(name: String) {
        require(!isLogin)
        this.name = name
        isLogin = true
    }

    fun rename(newName: String) {
        require(isLogin)
        name = newName
    }

    private companion object {
        const val IS_LOGIN = "IS_LOGIN"
        const val NAME = "NAME"
        const val TOKEN = "TOKEN"
    }
}