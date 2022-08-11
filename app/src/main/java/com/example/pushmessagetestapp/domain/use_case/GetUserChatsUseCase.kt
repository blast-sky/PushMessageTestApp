package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class GetUserChatsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(userId: String): Flow<List<Chat>> =
        repository.getUserChats(userId)
}