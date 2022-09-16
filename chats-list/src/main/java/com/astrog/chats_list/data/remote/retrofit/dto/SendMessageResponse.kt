package com.astrog.chats_list.data.remote.retrofit.dto

import com.google.gson.annotations.SerializedName

data class SendMessageResponse(
    @SerializedName("message_id") val messageId: String,
)
