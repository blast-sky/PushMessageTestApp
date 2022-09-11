package com.astrog.login.data

import com.astrog.login.R
import com.astrog.login.domain.repository.Resources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidResources @Inject constructor(
    private val resources: android.content.res.Resources
) : Resources {

    override val noOneUserWithNameAndPassword: String
        get() = resources.getString(R.string.not_valid_username_or_password)

    override val userWithSameNameAlreadyExist: String
        get() = resources.getString(R.string.user_with_same_name_already_exist)

    override val passwordsNotSame: String
        get() = resources.getString(R.string.passwords_not_same)

    override val passwordMustBeNonEmpty: String
        get() = resources.getString(R.string.password_must_be_non_empty)

    override val nameMustBeNonEmpty: String
        get() = resources.getString(R.string.username_must_be_non_empty)

    override val nameMustIncludeCharacters: String
        get() = resources.getString(R.string.username_must_include_characters)
}