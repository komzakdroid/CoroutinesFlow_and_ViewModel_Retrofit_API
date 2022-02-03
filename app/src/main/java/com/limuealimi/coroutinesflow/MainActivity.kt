package com.limuealimi.coroutinesflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.limuealimi.coroutinesflow.databinding.ActivityMainBinding
import com.limuealimi.coroutinesflow.utils.NetworkHelper
import com.limuealimi.coroutinesflow.utils.UserResource
import com.limuealimi.coroutinesflow.viewmodels.UserViewModel
import com.limuealimi.coroutinesflow.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    //private lateinit var flow: Flow<Int>
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkHelper = NetworkHelper(this)
        userViewModel =
            ViewModelProvider(this, ViewModelFactory(networkHelper))[UserViewModel::class.java]

        userViewModel.getUsers().observe(this, Observer {
            when (it) {
                is UserResource.Loading -> {

                }
                is UserResource.Error -> {
                    Log.d(TAG, "onCreate: ${it.message}")
                }
                is UserResource.Success -> {
                    Log.d(TAG, "onCreate: ${it.list}")
                }
            }
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