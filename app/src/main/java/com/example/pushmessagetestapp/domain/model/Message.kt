package com.example.pushmessagetestapp.domain.model

import com.google.firebase.Timestamp

data class Message(
    val created: Timestamp,
    val from: String,
    val message: String
)
