package com.example.pushmessagetestapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pushmessagetestapp.data.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.data.StoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalMainScreenViewModel @Inject constructor(
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
    private val storeUtil: StoreUtil
): ViewModel() {

    val isSignInState = mutableStateOf(sharedPreferencesUserUtil.isLogin)

    fun register(name: String) {
        sharedPreferencesUserUtil.register(name)
        
    }

}