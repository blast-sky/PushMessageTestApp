package com.astrog.chats_list.domain.use_case

import com.astrog.chats_list.domain.repository.Repository
import javax.inject.Inject

class UnLoginUseCase @Inject constructor(private val repository: Repository){

    operator fun invoke() = repository.unLogin()
}