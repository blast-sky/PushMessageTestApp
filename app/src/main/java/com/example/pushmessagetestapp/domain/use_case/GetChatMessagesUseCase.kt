package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class GetChatMessagesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(chatId: String): Flow<List<Message>> =
        repository.getChatMessages(chatId)
}