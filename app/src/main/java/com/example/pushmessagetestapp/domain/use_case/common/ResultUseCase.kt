package com.example.pushmessagetestapp.domain.use_case.common

import com.example.pushmessagetestapp.common.Result

interface ResultUseCase<T> {

    suspend fun loadResult(loader: suspend () -> T): Result<T> = try {
        val value = loader.invoke()
        Result.Success(value)
    } catch (e: Exception) {
        Result.Failure(e)
    }
}