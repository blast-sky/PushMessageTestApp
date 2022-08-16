package com.example.pushmessagetestapp.data.dto.firestore

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class UserDto(
    @DocumentId val id: String = "",
    @PropertyName("name") val name: String = "",
    @PropertyName("messageToken") val messageToken: String = "",
)
