package com.astrog.login.data

import com.astrog.login.domain.repository.Repository
import com.astrog.shared_preference.SharedPreferencesUserUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(
    private val loginFirestoreUtil: LoginFirestoreUtil,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) : Repository {

    override suspend fun login(name: String, password: String): String =
        loginFirestoreUtil.findUserByNameAndPassword(name, password)?.id ?: ""

    override suspend fun register(name: String, password: String): String =
        loginFirestoreUtil.registerNewUser(name, password)

    override val isLogin: Boolean
        get() = sharedPreferencesUserUtil.isLogin

    override fun localLogin(userId: String, name: String) =
        sharedPreferencesUserUtil.login(userId, name)
}