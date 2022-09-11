package com.astrog.login.data

import com.astrog.login.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(
    private val loginFirestoreUtil: LoginFirestoreUtil
) : Repository {

    override suspend fun login(name: String, password: String): String =
        loginFirestoreUtil.findUserByNameAndPassword(name, password)?.id ?: ""

    override suspend fun register(name: String, password: String): String =
        loginFirestoreUtil.registerNewUser(name, password)
}