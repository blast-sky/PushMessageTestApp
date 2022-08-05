package com.example.pushmessagetestapp.presentation

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import java.util.*

object PreviewModels {

    val date: Date = Calendar.getInstance().let {
        it.set(2020, 7, 14, 17, 31)
        it.time
    }

    val chat = Chat(
        id = "11111111111111111",
        users = listOf(User("2222222222222222", "4444444444444", "Valera")),
        messages = listOf(
            Message(
                "333333333333333",
                Calendar.getInstance().time,
                "11111111111111111",
                "hi"
            )
        )
    )

    val message = Message(
        id = "123",
        created = date,
        from = "user1",
        message = "test hi"
    )


}