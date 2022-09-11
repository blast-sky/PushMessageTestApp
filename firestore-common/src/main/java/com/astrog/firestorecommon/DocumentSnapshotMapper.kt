package com.astrog.firestorecommon

import com.astrog.firestorecommon.dto.ChatDto
import com.astrog.firestorecommon.dto.MessageDto
import com.astrog.firestorecommon.dto.UserDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

fun DocumentSnapshot.toUserDto() = requireNotNull(toUserDtoOrNull()) {
    "Can't transform DocumentSnapshot to UserDto"
}

fun DocumentSnapshot.toMessageDto() = requireNotNull(toMessageDtoOrNull()) {
    "Can't transform DocumentSnapshot to MessageDto"
}

fun DocumentSnapshot.toChatDto() = requireNotNull(toChatDtoOrNull()) {
    "Can't transform DocumentSnapshot to ChatDto"
}

fun DocumentSnapshot.toChatDtoOrNull() = toObject<ChatDto>()

fun DocumentSnapshot.toMessageDtoOrNull() = toObject<MessageDto>()

fun DocumentSnapshot.toUserDtoOrNull() = toObject<UserDto>()