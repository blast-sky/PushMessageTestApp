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

    var userId
        get() = sharedPreferences.getString(USER_ID, "")!!
        set(value) = sharedPreferences.edit { putString(USER_ID, value) }

    var messageToken
        get() = sharedPreferences.getString(MESSAGE_TOKEN, "")!!
        set(value) = sharedPreferences.edit { putString(MESSAGE_TOKEN, value) }

    var isLogin
        get() = sharedPreferences.getBoolean(IS_LOGIN, false)
        private set(value) = sharedPreferences.edit { putBoolean(IS_LOGIN, value) }

    var name
        get() = sharedPreferences.getString(NAME, "")!!
        private set(value) = sharedPreferences.edit { putString(NAME, value) }

    fun register(userId: String, name: String) {
        require(!isLogin)
        this.userId = userId
        this.name = name
        this.isLogin = true
    }

    fun rename(newName: String) {
        require(isLogin)
        name = newName
    }

    private companion object {
        const val USER_ID = "USER_ID"
        const val IS_LOGIN = "IS_LOGIN"
        const val NAME = "NAME"
        const val MESSAGE_TOKEN = "MESSAGE_TOKEN"
    }
}