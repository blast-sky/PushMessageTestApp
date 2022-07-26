package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetAvailableUsersForNewChatUseCase @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(): List<User> =
        repository
            .getAvailableUsers(repository.userId)
            .filter { user -> user.id != repository.userId }
}
