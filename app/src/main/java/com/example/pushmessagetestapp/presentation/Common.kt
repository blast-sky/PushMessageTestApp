package com.example.pushmessagetestapp.presentation

import com.example.pushmessagetestapp.common.Resource
import com.example.pushmessagetestapp.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> loadResource(
    errorMessage: String,
    loader: suspend () -> Result<T>
): Flow<Resource<T>> = flow {
    emit(Resource.Loading())
    val resource = when (val result = loader.invoke()) {
        is Result.Failure -> Resource.Error(errorMessage, result.error)
        is Result.Success -> Resource.Success(result.value)
    }
    emit(resource)
}

fun <T> loadFlowableResource(
    errorMessage: String,
    loader: suspend () -> Result<Flow<T>>,
) = loadFlowableResource(
    errorMessage = errorMessage,
    transformation = Transformation.Default(),
    loader = loader,
)

fun <T, R> loadFlowableResource(
    errorMessage: String,
    transformation: Transformation<T, R>,
    loader: suspend () -> Result<Flow<T>>
): Flow<Resource<R>> = flow {
    emit(Resource.Loading())
    when (val flow = loader.invoke()) {
        is Result.Failure -> emit(Resource.Error(errorMessage, flow.error))
        is Result.Success -> flow.value.collect { value ->
            emit(Resource.Success(transformation.transform(value)))
        }
    }
}

fun interface Transformation<in T, out R> {

    fun transform(i: T): R

    @Suppress("UNCHECKED_CAST")
    class Default<T> : Transformation<T, T> {
        override fun transform(i: T): T = i
    }
}