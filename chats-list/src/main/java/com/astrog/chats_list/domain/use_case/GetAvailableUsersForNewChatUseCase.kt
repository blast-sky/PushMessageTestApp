package com.astrog.chats_list.domain.use_case

import com.astrog.chats_list.domain.model.User
import com.astrog.chats_list.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetAvailableUsersForNewChatUseCase @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(): List<User> =
        repository
            .getAvailableUsers()
            .filter { user -> user.id != repository.userId }
}
