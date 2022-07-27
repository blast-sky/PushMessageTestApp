package com.example.pushmessagetestapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.StateObject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.data.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.data.StoreUtil
import com.example.pushmessagetestapp.data.repository.RepositoryImpl
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalMainScreenViewModel @Inject constructor(
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        viewModelScope.launch {
            state = state.copy(isLogin = sharedPreferencesUserUtil.isLogin)
            if (sharedPreferencesUserUtil.isLogin) {
                state = state.copy(chats = Resource.Loading())
                val chats = repository.getUserChats(sharedPreferencesUserUtil.messageToken)
                state = state.copy(chats = Resource.Success(chats))
            }
        }
    }

    fun register(name: String) = viewModelScope.launch {
        sharedPreferencesUserUtil.register(name)
            //storeUtil.addNewUser(User(sharedPreferencesUserUtil.messageToken, name))
    }

    private fun getChats() = viewModelScope.launch {
        //storeUtil.getUserChats(sharedPreferencesUserUtil.messageToken)
    }

}