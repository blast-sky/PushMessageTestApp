package com.example.pushmessagetestapp.presentation

import com.example.pushmessagetestapp.common.Resource
import kotlinx.coroutines.flow.*

fun <T> loadResource(
    errorMessage: String,
    loader: suspend () -> T
): Flow<Resource<T>> = flow {
    emit(Resource.Loading())
    val result = loader.invoke()
    val resource = Resource.Success(result)
    emit(resource)
}.catch { t -> emit(Resource.Error(errorMessage, t)) }

fun <T> loadFlowableResource(
    errorMessage: String,
    loader: suspend () -> Flow<T>,
) = loadFlowableResource(
    errorMessage = errorMessage,
    transformation = Transformation.Default(),
    loader = loader,
)

fun <T, R> loadFlowableResource(
    errorMessage: String,
    transformation: Transformation<T, R>,
    loader: suspend () -> Flow<T>
): Flow<Resource<R>> = flow {
    emit(Resource.Loading())
    loader.invoke()
        .onEach { emit(Resource.Success(transformation.transform(it))) }
        .collect()
}.catch { t -> emit(Resource.Error(errorMessage, t)) }

fun interface Transformation<in T, out R> {

    fun transform(i: T): R

    class Default<T> : Transformation<T, T> {
        override fun transform(i: T): T = i
    }
}