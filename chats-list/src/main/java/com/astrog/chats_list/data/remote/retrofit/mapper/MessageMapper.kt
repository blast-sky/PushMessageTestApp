package com.astrog.chats_list.data.remote.retrofit.mapper

import com.astrog.chats_list.domain.model.Message
import com.astrog.firestorecommon.dto.MessageDto


fun MessageDto.toMessage() = Message(
    id = id,
    created = created.toDate(),
    from = from,
    message = message,
    image = image,
)