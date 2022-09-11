package com.astrog.login.data

import com.astrog.firestorecommon.CommonFirestoreUtil
import com.astrog.firestorecommon.dto.UserDto
import com.astrog.firestorecommon.suspend
import com.astrog.firestorecommon.toUserDto
import com.astrog.login.data.exception.MoreThenOneUserWithCurrentUsernameAndPassword
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginFirestoreUtil @Inject constructor() : CommonFirestoreUtil() {

    suspend fun findUserByNameAndPassword(name: String, password: String): UserDto? =
        usersCollectionReference
            .whereEqualTo(Companion.Fields.User.NAME, name)
            .whereEqualTo(Companion.Fields.User.PASSWORD, password)
            .get()
            .suspend()
            .documents
            .apply { if (size > 1) throw MoreThenOneUserWithCurrentUsernameAndPassword() }
            .firstOrNull()
            ?.toUserDto()

    suspend fun registerNewUser(name: String, password: String): String {
        val userWithSameName = findUserByName(name)

        if (userWithSameName != null)
            return ""

        val userDto = UserDto(
            name = name,
            password = password,
            messageToken = FirebaseMessaging.getInstance().token.suspend()
        )

        return usersCollectionReference
            .add(userDto)
            .suspend()
            .id
    }

    private suspend fun findUserByName(name: String): UserDto? =
        usersCollectionReference
            .whereEqualTo(Companion.Fields.User.NAME, name)
            .get()
            .suspend()
            .documents
            .firstOrNull()
            ?.toUserDto()
}