package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class SendMessageUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(chatId: String, message: String, fromId: String) =
        repository.sendMessage(chatId, message, fromId)
}