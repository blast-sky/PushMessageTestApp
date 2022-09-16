package com.astrog.chats_list.domain.use_case

import com.astrog.chats_list.domain.model.Chat
import com.astrog.chats_list.domain.model.User
import com.astrog.chats_list.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class CreateChatWithMeUseCase @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(chat: Chat): String {
        val me = User(id = repository.userId)
        val chatWithMe = chat.copy(users = chat.users + me)
        return repository.createChat(chatWithMe)
    }
}