package com.astrog.login.domain.repository

interface Repository {

    suspend fun login(name: String, password: String): String

    suspend fun register(name: String, password: String): String

    fun localLogin(userId: String, name: String)

    val isLogin: Boolean
}