package com.astrog.chats_list.data.remote.retrofit.dto

import com.google.gson.annotations.SerializedName

data class NotificationInfo(
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
)
