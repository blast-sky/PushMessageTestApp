package com.astrog.chats_list.domain.use_case

import com.astrog.chats_list.domain.model.Chat
import com.astrog.chats_list.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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