package com.example.pushmessagetestapp.domain.model

import java.util.*

data class Message(
    val id: String,
    val created: Date,
    val from: String,
    val message: String
)
