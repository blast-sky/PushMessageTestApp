package com.astrog.chats_list.data.remote

import android.net.Uri
import com.astrog.chats_list.Config
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudStorage @Inject constructor() {

    private val storage = Firebase.storage(Config.STORAGE_PATH)
    private val imagesRef = storage.reference.child(IMAGES)

    private val randomFileName: String
        get() = UUID.randomUUID().toString()

    fun uploadImage(uri: Uri): Flow<UploadResult> {
        val ref = imagesRef.child(randomFileName)
        return ref
            .putFile(uri)
            .asFlow()
            .onCompletion { emit(UploadResult.Success(ref.downloadUrl.suspend().toString())) }
    }

    private fun UploadTask.asFlow(): Flow<UploadResult> = callbackFlow {
        addOnCanceledListener { trySendBlocking(UploadResult.Failure()) }
        addOnFailureListener { ex -> trySendBlocking(UploadResult.Failure(ex)) }
        addOnPausedListener { trySendBlocking(UploadResult.Pause) }
        addOnProgressListener { progress ->
            val percent =
                ((progress.bytesTransferred.toFloat() / progress.totalByteCount) * 100).toInt()
            trySendBlocking(UploadResult.Loading(percent))
        }
        addOnCompleteListener { close() }
        awaitClose {}
        // TODO add await close
    }

    private companion object {
        const val IMAGES = "images"
    }
}