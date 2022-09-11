package com.astrog.login.domain.model

import androidx.core.text.isDigitsOnly
import com.astrog.common.Resource
import com.astrog.login.domain.repository.Resources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistrationNameAndPasswordsChecker @Inject constructor(private val resources: Resources) {

    fun checkValidity(name: String, password: String, repeatPassword: String): Resource<Unit> {
        if (name.isBlank()) return Resource.Error(resources.nameMustBeNonEmpty)
        if (name.isDigitsOnly()) return Resource.Error(resources.nameMustIncludeCharacters)
        if (password.isBlank()) return Resource.Error(resources.passwordMustBeNonEmpty)
        if (password != repeatPassword) return Resource.Error(resources.passwordsNotSame)

        return Resource.Success(Unit)
    }
}