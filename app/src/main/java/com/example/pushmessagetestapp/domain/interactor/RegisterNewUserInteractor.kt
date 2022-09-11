package com.example.pushmessagetestapp.domain.interactor

import com.astrog.common.Resource
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.domain.repository.Resources
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