package com.limuealimi.coroutinesflow.repository

import com.limuealimi.coroutinesflow.models.User
import com.limuealimi.coroutinesflow.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiService: ApiService) {
    fun getUsers() = flow { emit(apiService.getUsers()) }
}