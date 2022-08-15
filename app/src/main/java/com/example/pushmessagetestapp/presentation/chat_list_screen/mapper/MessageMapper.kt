package com.example.pushmessagetestapp.presentation.chat_list_screen.mapper

import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.MessagePresenterModel
import java.text.SimpleDateFormat
import java.util.*

private fun formatDate(date: Date): String = SimpleDateFormat("dd MMM HH:mm").format(date)

fun Message.toMessagePresenterModel(from: String) = MessagePresenterModel(
    message = message,
    from = from,
    date = formatDate(date = created),
)