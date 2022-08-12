package com.example.pushmessagetestapp.data.remote.retrofit

import com.google.gson.annotations.SerializedName

data class NotificationInfo(
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
)
