package com.astrog.chats_list.data.remote

import com.astrog.chats_list.data.remote.retrofit.dto.NotificationInfo
import com.astrog.chats_list.data.remote.retrofit.dto.SendMessageBody
import com.astrog.chats_list.data.remote.retrofit.MessagingService
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessagingUtil @Inject constructor(
    private val messagingService: MessagingService,
) {

    suspend fun getToken(): String = FirebaseMessaging.getInstance().token.suspend()

    suspend fun sendMessage(messageToken: String, message: String, title: String) = messagingService
        .sendMessage(
            sendMessageBody = SendMessageBody(
                to = messageToken,
                notification = NotificationInfo(
                    title = title,
                    body = message,
                )
            )
        )

    suspend fun sendMessages(messageTokens: List<String>, message: String, title: String) =
        messageTokens.forEach { token ->
            sendMessage(token, message, title)
        }
}