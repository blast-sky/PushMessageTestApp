package com.example.pushmessagetestapp.data.mapper.dto

import com.example.pushmessagetestapp.data.dto.firestore.MessageDto
import com.example.pushmessagetestapp.domain.model.Message


fun MessageDto.toMessage() = Message(
    id = id,
    created = created.toDate(),
    from = from,
    message = message,
)