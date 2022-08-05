package com.example.pushmessagetestapp.presentation.chat_list_screen

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.model.Chat
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
    private val repository: Repository,
) : ViewModel() {

    var chats: Resource<List<Chat>> by mutableStateOf(Resource.Created())

    init {
        viewModelScope.launch { loadChats() }
    }

    private suspend fun loadChats() {
        chats = Resource.Loading()
        chats = try {
            val loadedChats = repository.getUserChats(sharedPreferencesUserUtil.userId)
            Resource.Success(loadedChats)
        } catch (e: Exception) {
            Resource.Error(resources.getString(R.string.chat_load_error), e)
        }
    }


}