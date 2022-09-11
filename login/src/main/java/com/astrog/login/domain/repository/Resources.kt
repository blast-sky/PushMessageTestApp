package com.astrog.login.domain.repository

interface Resources {

    val noOneUserWithNameAndPassword: String

    val userWithSameNameAlreadyExist: String

    val passwordsNotSame: String

    val passwordMustBeNonEmpty: String

    val nameMustBeNonEmpty: String

    val nameMustIncludeCharacters: String
}