package com.astrog.login.data.exception

class MoreThenOneUserWithCurrentUsernameAndPassword(
    message: String = ""
) : RuntimeException(message)