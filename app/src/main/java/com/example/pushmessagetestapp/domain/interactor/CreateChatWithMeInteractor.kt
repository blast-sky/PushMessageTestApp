package com.example.pushmessagetestapp.domain.interactor

import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateChatWithMeInteractor @Inject constructor(
    private val repository: Repository,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) {

    suspend operator fun invoke(chat: Chat): String {
        val me = User(id = sharedPreferencesUserUtil.userId)
        val chatWithMe = chat.copy(users = chat.users + me)
        return repository.createChat(chatWithMe)
    }
}