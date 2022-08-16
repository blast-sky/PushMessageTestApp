package com.example.pushmessagetestapp.data.dto.retrofit

import com.google.gson.annotations.SerializedName

data class SendMessageResponse(
    @SerializedName("message_id") val messageId: String,
)
