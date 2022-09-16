package com.astrog.chats_list.domain.interactor

import com.astrog.chats_list.domain.model.User
import com.astrog.common.Resource
import com.astrog.chats_list.domain.repository.Repository
import com.astrog.chats_list.domain.repository.Resources
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RegisterNewUserInteractor @Inject constructor(
    private val resources: Resources,
    private val repository: Repository,
) {

    suspend operator fun invoke(name: String): Resource<Boolean> {
        val user = User(name = name, messageToken = repository.getMessagingToken())
        val id = repository.registerNewUser(user)

        return if (id.isNotBlank())
            Resource.Success(true)
        else
            Resource.Error(resources.loginError)
    }
}