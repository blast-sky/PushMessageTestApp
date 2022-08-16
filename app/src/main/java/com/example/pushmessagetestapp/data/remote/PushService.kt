package com.example.pushmessagetestapp.data.remote

import android.util.Log
import com.example.pushmessagetestapp.domain.repository.Repository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.*
import javax.inject.Inject

@ServiceScoped
class PushService @Inject constructor() : FirebaseMessagingService() {

    @Inject
    lateinit var repository: Repository

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        coroutineScope.launch { repository.updateMessagingToken(token) }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("Tag", message.notification?.title.toString())
        Log.d("Tag", message.notification?.body.toString())
    }
}