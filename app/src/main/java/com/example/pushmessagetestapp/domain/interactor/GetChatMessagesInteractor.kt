package com.example.pushmessagetestapp.domain.interactor

import com.example.pushmessagetestapp.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetChatMessagesInteractor @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(chatId: String) = repository.getChatMessages(chatId)

}