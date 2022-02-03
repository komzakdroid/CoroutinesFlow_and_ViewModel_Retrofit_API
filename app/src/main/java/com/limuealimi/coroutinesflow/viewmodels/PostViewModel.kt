package com.limuealimi.coroutinesflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limuealimi.coroutinesflow.models.Post
import com.limuealimi.coroutinesflow.models.User
import com.limuealimi.coroutinesflow.models.UserWithPost
import com.limuealimi.coroutinesflow.repository.UserRepository
import com.limuealimi.coroutinesflow.retrofit.ApiClient
import com.limuealimi.coroutinesflow.utils.NetworkHelper
import com.limuealimi.coroutinesflow.utils.UserResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class PostViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val liveData = MutableLiveData<UserResource>()
    private val userRepository = UserRepository(ApiClient.apiService)

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                liveData.postValue(UserResource.Loading)
                val userWithPost = UserWithPost()
                userRepository.getUsers().flatMapConcat {
                    userWithPost.userList = it.body()
                    userRepository.getPosts()
                }.collect {
                    userWithPost.postList = it.body()
                    liveData.postValue(UserResource.Success(userWithPost))

                }
            }
        } else {
            liveData.postValue(UserResource.Error("No internet connection"))
        }
    }

    fun getUserWithPost(): LiveData<UserResource> {
        return liveData
    }
}