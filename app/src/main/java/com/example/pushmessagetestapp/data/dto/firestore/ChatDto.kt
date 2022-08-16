package com.example.pushmessagetestapp.data.dto.firestore

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class ChatDto(
    @DocumentId val id: String = "",
    @PropertyName("users") val users: List<String> = emptyList(),
)
