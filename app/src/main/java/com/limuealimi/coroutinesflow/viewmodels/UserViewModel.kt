package com.limuealimi.coroutinesflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limuealimi.coroutinesflow.repository.UserRepository
import com.limuealimi.coroutinesflow.retrofit.ApiClient
import com.limuealimi.coroutinesflow.utils.NetworkHelper
import com.limuealimi.coroutinesflow.utils.UserResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val liveData = MutableLiveData<UserResource>()
    private val userRepository = UserRepository(ApiClient.apiService)

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                liveData.postValue(UserResource.Loading)
                userRepository.getUsers()
                    .catch {
                        liveData.postValue(UserResource.Error(it.message ?: ""))
                    }
                    .collect {
                        if (it.isSuccessful) {
                            liveData.postValue(UserResource.Success(it.body() ?: emptyList()))
                        }
                    }
            }
        } else {
            liveData.postValue(UserResource.Error("No internet connection"))
        }
    }

    fun getUsers(): LiveData<UserResource> {
        return liveData
    }
}