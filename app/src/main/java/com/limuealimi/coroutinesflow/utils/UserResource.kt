package com.limuealimi.coroutinesflow.utils

import com.limuealimi.coroutinesflow.models.User
import java.lang.Exception

sealed class UserResource {
    object Loading : UserResource()

    class Success(val list: List<User>) : UserResource()

    class Error(val message: String) : UserResource()
}