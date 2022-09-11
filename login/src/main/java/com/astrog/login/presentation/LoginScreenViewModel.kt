package com.astrog.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astrog.common.Resource
import com.astrog.common.map
import com.astrog.login.domain.model.RegistrationNameAndPasswordsChecker
import com.astrog.login.domain.repository.Resources
import com.astrog.login.domain.use_case.LoginUseCase
import com.astrog.login.domain.use_case.RegisterNewUserUseCase
import com.astrog.shared_preference.SharedPreferencesUserUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val resources: Resources,
    private val registerNewUserUseCase: RegisterNewUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val nameAndPasswordsChecker: RegistrationNameAndPasswordsChecker,
    private val sharedPreferences: SharedPreferencesUserUtil,
) : ViewModel() {

    var isLogin: Resource<Boolean> by mutableStateOf(Resource.Success(sharedPreferences.isLogin)) // TODO
    var loginState: Resource<String> by mutableStateOf(Resource.Success(""))
    var registerState: Resource<String> by mutableStateOf(Resource.Success(""))
    var userId: String? = null

    fun changeLoginStateIfError() {
        if (loginState is Resource.Error)
            loginState = Resource.Success("")
    }

    fun changeRegisterStateIfError() {
        if (registerState is Resource.Error)
            registerState = Resource.Success("")
    }

    private val handler = CoroutineExceptionHandler { _, _ ->
        isLogin = Resource.Error("")
    }

    fun register(
        name: String,
        password: String,
        repeatPassword: String
    ) {
        val checkResult = nameAndPasswordsChecker.checkValidity(name, password, repeatPassword)
        if (checkResult is Resource.Error) {
            registerState = checkResult.map { "" }
            return
        }

        registerNewUserUseCase
            .invoke(name, password)
            .onEach { registerState = it }
            .onCompletion {
                val state = registerState
                if (state is Resource.Success && it == null) {
                    sharedPreferences.login(state.value, name)
                    isLogin = Resource.Success(sharedPreferences.isLogin)
                }
            }
            .launchIn(viewModelScope)
    }

    fun login(
        name: String,
        password: String
    ) = loginUseCase
        .invoke(name, password)
        .onEach { loginState = it }
        .onCompletion {
            val state = loginState
            if (state is Resource.Success && it == null) {
                sharedPreferences.login(state.value, name)
                isLogin = Resource.Success(sharedPreferences.isLogin)
            }
        }
        .launchIn(viewModelScope)
}