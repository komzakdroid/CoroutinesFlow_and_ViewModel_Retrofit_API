package com.limuealimi.coroutinesflow.retrofit

import com.limuealimi.coroutinesflow.models.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}