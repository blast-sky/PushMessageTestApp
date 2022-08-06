package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.common.Result
import com.example.pushmessagetestapp.domain.model.Message
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.domain.use_case.common.ResultUseCase
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class GetChatMessagesUseCase @Inject constructor(
    private val repository: Repository
) : ResultUseCase<Flow<List<Message>>> {

    suspend operator fun invoke(chatId: String): Result<Flow<List<Message>>> =
        loadResult { repository.getChatMessages(chatId) }
}