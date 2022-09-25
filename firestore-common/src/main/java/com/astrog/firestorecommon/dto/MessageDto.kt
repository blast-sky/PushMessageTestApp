package com.astrog.firestorecommon.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class MessageDto(
    @DocumentId val id: String = "",
    @PropertyName("from") val from: String = "",
    @PropertyName("message") val message: String = "",
    @PropertyName("created") val created: Timestamp = Timestamp.now(),
    @PropertyName("image") val image: String = "",
)
