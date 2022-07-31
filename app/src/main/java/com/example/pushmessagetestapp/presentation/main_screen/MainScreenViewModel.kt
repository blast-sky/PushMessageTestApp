package com.example.pushmessagetestapp.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.data.remote.MessagingUtil
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
    private val repository: Repository,
    private val messagingUtil: MessagingUtil,
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        viewModelScope.launch {
            val isLogin = sharedPreferencesUserUtil.isLogin
            state = state.copy(isLogin = isLogin)
            if (isLogin) loadChats()
        }
    }

    private suspend fun loadChats() {
        state = state.copy(chats = Resource.Loading())
        state = try {
            val chats = repository.getUserChats(sharedPreferencesUserUtil.userId)
            state.copy(chats = Resource.Success(chats))
        } catch (e: Exception) {
            state.copy(chats = Resource.Error(e))
        }
    }

    fun register(name: String) = viewModelScope.launch {
        val newUserId = repository.addNewUser(User("", messagingUtil.getToken(), name))
        if (newUserId.isNotEmpty()) {
            sharedPreferencesUserUtil.register(newUserId, name)
            state = state.copy(isLogin = true)
            loadChats()
        }
    }
}