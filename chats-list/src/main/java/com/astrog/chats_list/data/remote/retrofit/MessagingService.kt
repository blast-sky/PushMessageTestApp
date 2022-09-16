package com.astrog.chats_list.data.remote.retrofit

import com.astrog.chats_list.Config
import com.astrog.chats_list.data.remote.retrofit.dto.SendMessageBody
import com.astrog.chats_list.data.remote.retrofit.dto.SendMessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MessagingService {

    @POST("/fcm/send")
    @Headers("Content-Type:application/json")
    suspend fun sendMessage(
        @Body sendMessageBody: SendMessageBody,
    ): Response<SendMessageResponse>

    companion object {
        const val BASE_URL = Config.BASE_URL
        const val KEY = "key=${Config.KEY}"
    }
}