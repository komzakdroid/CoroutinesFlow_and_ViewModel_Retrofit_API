package com.limuealimi.coroutinesflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.limuealimi.coroutinesflow.databinding.ActivityMainBinding
import com.limuealimi.coroutinesflow.utils.NetworkHelper
import com.limuealimi.coroutinesflow.utils.UserResource
import com.limuealimi.coroutinesflow.viewmodels.MyViewModel
import com.limuealimi.coroutinesflow.viewmodels.PostViewModel
import com.limuealimi.coroutinesflow.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var myViewModel: MyViewModel

    //private lateinit var flow: Flow<Int>
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkHelper = NetworkHelper(this)
        postViewModel =
            ViewModelProvider(this, ViewModelFactory(networkHelper))[PostViewModel::class.java]
        myViewModel =
            ViewModelProvider(this, ViewModelFactory(networkHelper))[MyViewModel::class.java]


        postViewModel.getUserWithPost().observe(this) {
            when (it) {
                is UserResource.Loading -> {
                    Log.d(tag, "onCreate: Loading...")
                }
                is UserResource.Error -> {
                    Log.d(tag, "onCreate: ${it.message}")
                }
                is UserResource.Success -> {
                    Log.d(tag, "onCreate: ${it.userWithPost.userList}")
                    Log.d(tag, "onCreate: ${it.userWithPost.postList}")
                }
            }
        }
        myViewModel.liveEvent.postValue("PDP")
        myViewModel.liveEvent.observe(this, Observer {

        })

        /* setupFlow()
         setupClick()*/
    }
/*
    private fun setupClick() {
        binding.btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                flow.collect {
                    Log.d(tag, "setupClick: $it")
                    Toast.makeText(this@MainActivity, "Clicked!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setupFlow() {
        flow = flow {
            Log.d(tag, "setupFlow: Start Flow ")
            (0..10).forEach {
                delay(2000)
                Log.d(tag, "Emitting:$it ")
                emit(it)
            }
        }.flowOn(Dispatchers.Default)
    }*/
}