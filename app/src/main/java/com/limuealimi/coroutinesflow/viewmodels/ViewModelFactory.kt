package com.limuealimi.coroutinesflow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.limuealimi.coroutinesflow.utils.NetworkHelper
import java.lang.Exception

class ViewModelFactory(private val networkHelper: NetworkHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(networkHelper) as T
        }
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(networkHelper) as T
        }
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel() as T
        }
        throw Exception("Error")
    }
}