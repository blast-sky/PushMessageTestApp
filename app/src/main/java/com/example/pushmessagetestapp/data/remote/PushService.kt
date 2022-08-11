package com.example.pushmessagetestapp.data.remote

import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject

@ServiceScoped
class PushService @Inject constructor(
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil
) : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}