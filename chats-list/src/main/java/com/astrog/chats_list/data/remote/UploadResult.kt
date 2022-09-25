package com.astrog.chats_list.data.remote

sealed class UploadResult {

    data class Loading(val percent: Int) : UploadResult()

    data class Failure(val exception: Throwable? = null) : UploadResult()

    object Pause : UploadResult()

    data class Success(val uri: String) : UploadResult()
}
