package com.example.pushmessagetestapp.domain.use_case

import com.example.pushmessagetestapp.common.Result
import com.example.pushmessagetestapp.data.local.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.domain.model.User
import com.example.pushmessagetestapp.domain.repository.Repository
import com.example.pushmessagetestapp.domain.use_case.common.ResultUseCase
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetAvailableUsersUseCase @Inject constructor(
    private val repository: Repository,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil,
) : ResultUseCase<List<User>> {

    suspend operator fun invoke(): Result<List<User>> =
        loadResult { repository.getAvailableUsers(sharedPreferencesUserUtil.userId) }
}
