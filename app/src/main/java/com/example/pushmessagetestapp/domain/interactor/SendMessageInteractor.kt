package com.example.pushmessagetestapp.domain.interactor

import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class SendMessageInteractor @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(chatId: String, message: String, fromId: String) =
        repository.sendMessage(chatId, message, fromId)
}