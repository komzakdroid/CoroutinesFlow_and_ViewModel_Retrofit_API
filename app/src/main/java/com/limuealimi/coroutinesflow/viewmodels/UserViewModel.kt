package com.limuealimi.coroutinesflow.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limuealimi.coroutinesflow.retrofit.ApiClient
import com.limuealimi.coroutinesflow.utils.UserResource
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val liveData = MutableLiveData<UserResource>()
    private val apiService = ApiClient.apiService

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            liveData.postValue(UserResource.Loading)
            apiService.getUsers()
        }
    }
}