package com.example.pushmessagetestapp.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class SharedPreferencesUserUtil @Inject constructor(
    @Named("Login") private val sharedPreferences: SharedPreferences
) {

    var userId by SharedPreferenceDelegate.String(sharedPreferences, USER_ID, "")

    var messageToken by SharedPreferenceDelegate.String(sharedPreferences, MESSAGE_TOKEN, "")

    var isLogin by SharedPreferenceDelegate.Boolean(sharedPreferences, IS_LOGIN, false)

    var name by SharedPreferenceDelegate.String(sharedPreferences, NAME, "")

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
        const val NAME = "NAME"
        const val USER_ID = "USER_ID"
        const val IS_LOGIN = "IS_LOGIN"
        const val MESSAGE_TOKEN = "MESSAGE_TOKEN"
    }
}