package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ActivityRetainedScoped
class GetUserChatsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): Flow<List<Chat>> =
        repository
            .getUserChats(repository.userId)
            .map { chats -> chats.sortedByDescending { chat -> chat.messages.lastOrNull()?.created } }
}