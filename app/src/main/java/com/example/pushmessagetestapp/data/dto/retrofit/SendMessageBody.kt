package com.example.pushmessagetestapp.data.dto.retrofit

import com.google.gson.annotations.SerializedName

data class SendMessageBody(
    @SerializedName("to") val to: String,
    @SerializedName("notification") val notification: NotificationInfo,
    @SerializedName("direct_boot_ok") val flag: Boolean = true,
)
