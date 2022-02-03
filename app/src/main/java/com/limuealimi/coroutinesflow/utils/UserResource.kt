package com.limuealimi.coroutinesflow.utils

import com.limuealimi.coroutinesflow.models.User
import com.limuealimi.coroutinesflow.models.UserWithPost
import java.lang.Exception

sealed class UserResource {
    object Loading : UserResource()

    class Success(val userWithPost: UserWithPost) : UserResource()

    class Error(val message: String) : UserResource()
}