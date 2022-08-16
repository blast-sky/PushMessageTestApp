package com.example.pushmessagetestapp.data.mapper.raw

import com.example.pushmessagetestapp.data.dto.firestore.ChatDto
import com.example.pushmessagetestapp.data.dto.firestore.MessageDto
import com.example.pushmessagetestapp.data.dto.firestore.UserDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

fun DocumentSnapshot.toUserDto() = requireNotNull(toObject<UserDto>()) {
    "Can't transform DocumentSnapshot to UserDto"
}

fun DocumentSnapshot.toMessageDto() = requireNotNull(toObject<MessageDto>()) {
    "Can't transform DocumentSnapshot to MessageDto"
}

fun DocumentSnapshot.toChatDto() = requireNotNull(toObject<ChatDto>()) {
    "Can't transform DocumentSnapshot to ChatDto"
}