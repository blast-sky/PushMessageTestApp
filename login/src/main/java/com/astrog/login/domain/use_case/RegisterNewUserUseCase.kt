package com.astrog.login.domain.use_case

import com.astrog.common.Resource
import com.astrog.login.domain.repository.Repository
import com.astrog.login.domain.repository.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RegisterNewUserUseCase @Inject constructor(
    private val repository: Repository,
    private val resources: Resources,
) {
    operator fun invoke(name: String, password: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        val userId = repository.register(name, password)
        if (userId.isBlank())
            emit(Resource.Error(resources.userWithSameNameAlreadyExist))
        else
            emit(Resource.Success(userId))
    }
}