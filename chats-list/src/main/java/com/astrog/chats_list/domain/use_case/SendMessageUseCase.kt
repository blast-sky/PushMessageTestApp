package com.astrog.chats_list.domain.use_case

import com.astrog.chats_list.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class SendMessageUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(chatId: String, message: String): String {
        val messageId = repository.createMessage(chatId, message)

        if(messageId.isNotBlank()) {
            val userIds = repository.getOtherUserIdsInChat(chatId)
            userIds.forEach { id -> repository.sendMessage(id, message) }
        }

        return messageId
    }
}