package com.example.pushmessagetestapp.presentation.login_screen

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.R
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.data.remote.MessagingUtil
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
    private val repository: Repository,
    private val messagingUtil: MessagingUtil,
) : ViewModel() {

    var isLogin: Resource<Boolean> by mutableStateOf(Resource.Success(sharedPreferencesUserUtil.isLogin))

    fun register(name: String) = viewModelScope.launch {
        isLogin = Resource.Loading()
        val newUserId = repository.addNewUser(User("", messagingUtil.getToken(), name))
        isLogin = if (newUserId.isNotEmpty()) {
            sharedPreferencesUserUtil.register(newUserId, name)
            Resource.Success(true)
        } else {
            Resource.Error(resources.getString(R.string.login_error))
        }
    }
}