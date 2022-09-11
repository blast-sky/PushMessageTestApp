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

    var isLogin by SharedPreferenceDelegate.Boolean(sharedPreferences, IS_LOGIN, false)

    var name by SharedPreferenceDelegate.String(sharedPreferences, NAME, "")

    fun register(userId: String, name: String) {
        require(!isLogin) { "User must be unloged when use SharedPreferencesUserUtil::register" }
        this.userId = userId
        this.name = name
        this.isLogin = true
    }

    private companion object {
        const val NAME = "NAME"
        const val USER_ID = "USER_ID"
        const val IS_LOGIN = "IS_LOGIN"
    }
}