package com.limuealimi.coroutinesflow.viewmodels

import androidx.lifecycle.ViewModel
import uz.mobiler.flowsg.utils.SingleLiveEvent

class MyViewModel : ViewModel() {
    val liveEvent = SingleLiveEvent<String>()
}