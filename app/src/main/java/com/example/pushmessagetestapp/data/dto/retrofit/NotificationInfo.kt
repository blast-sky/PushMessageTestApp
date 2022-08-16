package com.example.pushmessagetestapp.data.dto.retrofit

import com.google.gson.annotations.SerializedName

data class NotificationInfo(
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
)
