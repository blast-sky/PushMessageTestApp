package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.common.Result
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.domain.use_case.common.ResultUseCase
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class GetUserChatsUseCase @Inject constructor(
    private val repository: Repository
) : ResultUseCase<Flow<List<Chat>>> {

    suspend operator fun invoke(userId: String): Result<Flow<List<Chat>>> =
        loadResult { repository.getUserChats(userId) }
}