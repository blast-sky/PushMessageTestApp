package com.astrog.chats_list.domain.use_case

import android.net.Uri
import android.util.Log
import com.astrog.chats_list.data.remote.UploadResult
import com.astrog.chats_list.domain.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ActivityRetainedScoped
class SendMessageUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(chatId: String, message: String, uri: Uri?): String {
        val uploadedUri = if (uri != null) {
            (repository
                .uploadImages(uri)
                .onEach { Log.d("MYTAG", it.toString()) }
                .last() as? UploadResult.Success)
                ?.let { it.uri }
                ?: ""
        } else ""

        val messageId = repository.createMessage(chatId, message, uploadedUri)

        if (messageId.isNotBlank()) {
            val userIds = repository.getOtherUserIdsInChat(chatId)
            userIds.forEach { id -> repository.sendMessage(id, message) }
        }

        return messageId
    }
}