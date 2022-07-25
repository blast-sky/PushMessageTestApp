package com.example.pushmessagetestapp.data.mapper

import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.util.Mapper
import com.google.firebase.firestore.DocumentSnapshot

object MessageMapper : Mapper<DocumentSnapshot, Message> {

    override fun map(value: DocumentSnapshot): Message = Message(
        created = value.getTimestamp(CREATED) ?: error("No '$CREATED' field in message"),
        from = value.getString(FROM) ?: error("No '$FROM' field in message"),
        message = value.getString(MESSAGE) ?: error("No '$MESSAGE' field in message"),
    )

    private const val CREATED = "created"
    private const val FROM = "from"
    private const val MESSAGE = "message"
}