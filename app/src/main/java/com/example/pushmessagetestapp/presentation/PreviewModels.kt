package com.example.pushmessagetestapp.presentation

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.ChatPresenterModel
import com.example.pushmessagetestapp.presentation.chat_list_screen.model.MessagePresenterModel
import java.util.*

object PreviewModels {

    val chatPresenterModel: ChatPresenterModel by lazy {
        ChatPresenterModel(
            title = "testChatTitle", id = "testChatId", lastMessage = MessagePresenterModel(
                from = "testUserName",
                message = "testUserMessage",
                date = "testChatDate",
            )
        )
    }

    val date: Date by lazy {
        Calendar.getInstance().run {
            set(2020, 7, 14, 17, 31)
            return@run time
        }
    }

    val chat by lazy {
        Chat(
            id = "testChatId",
            users = listOf(
                User(
                    id = "testUserId1",
                    name = "testUserName1",
                    messageToken = "testUserMessageToken1"
                )
            ),
            messages = listOf(
                Message(
                    id = "testMessageId1",
                    created = Calendar.getInstance().time,
                    from = "testUserId1",
                    message = "testMessage"
                )
            )
        )
    }

    val message by lazy {
        Message(
            id = "123",
            created = date,
            from = "user1",
            message = "test hi"
        )
    }

    val messages by lazy {
        listOf(
            message, message.copy(id = "1234", from = "user2")
        )
    }

    val userList by lazy {
        listOf(
            User(name = "testUserName1"),
            User(name = "testUserName2"),
            User(name = "testUserName3")
        )
    }

    const val chatTitle = "testChatTitle"

    const val chatId = "testChatId"

    const val userId = "testUserId"
}
