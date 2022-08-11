package com.example.pushmessagetestapp.data.repository

import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.domain.repository.Resources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidResources @Inject constructor(
    private val resources: android.content.res.Resources
) : Resources {

    override val loadChatError: String
        get() = resources.getString(R.string.chat_load_error)

    override val loadAvailableUsersError: String
        get() = resources.getString(R.string.get_available_users_error)

    override val loginError: String
        get() = resources.getString(R.string.login_error)
}