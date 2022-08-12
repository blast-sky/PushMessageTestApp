package com.example.pushmessagetestapp.data.remote.retrofit

import com.google.gson.annotations.SerializedName

data class SendMessageResponse(
    @SerializedName("message_id") val messageId: String,
)
