package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class GetChatMessagesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(chatId: String): Flow<List<Message>> =
        repository
            .getChatMessages(chatId)
            .map { messages -> messages.sortedBy { message -> message.created } }
}