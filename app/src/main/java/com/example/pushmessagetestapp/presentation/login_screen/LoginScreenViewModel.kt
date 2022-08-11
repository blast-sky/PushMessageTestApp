package com.example.pushmessagetestapp.presentation.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushmessagetestapp.common.Resource
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.repository.Resources
import com.example.pushmessagetestapp.domain.use_case.RegisterNewUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val registerNewUserUseCase: RegisterNewUserUseCase,
    sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) : ViewModel() {

    var isLogin: Resource<Boolean> by mutableStateOf(Resource.Success(sharedPreferencesUserUtil.isLogin))

    private val handler = CoroutineExceptionHandler { _, _ ->
        isLogin = Resource.Error(resources.loginError)
    }

    fun register(name: String) = viewModelScope.launch(handler) {
        isLogin = registerNewUserUseCase.invoke(name)
    }
}