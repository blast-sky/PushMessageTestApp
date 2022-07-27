package com.example.pushmessagetestapp.data.dto

import com.google.firebase.Timestamp
import kotlinx.serialization.SerialName

data class MessageDto(
    @SerialName("created") val created: Timestamp = Timestamp.now(),
    @SerialName("from") val from: String = "",
    @SerialName("message") val message: String = "",
)
