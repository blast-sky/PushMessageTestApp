package com.astrog.shared_preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesUserUtil @Inject constructor(
    @LoginSharedPreferences private val sharedPreferences: SharedPreferences
) {

    var userId by SharedPreferenceDelegate.String(sharedPreferences, USER_ID, "")

    var isLogin by SharedPreferenceDelegate.Boolean(sharedPreferences, IS_LOGIN, false)

    var name by SharedPreferenceDelegate.String(sharedPreferences, NAME, "")

    fun login(userId: String, name: String) {
        this.userId = userId
        this.name = name
        this.isLogin = true
    }

    fun unLogin() {
        userId = ""
        name = ""
        isLogin = false
    }

    private companion object {
        const val NAME = "NAME"
        const val USER_ID = "USER_ID"
        const val IS_LOGIN = "IS_LOGIN"
    }
}