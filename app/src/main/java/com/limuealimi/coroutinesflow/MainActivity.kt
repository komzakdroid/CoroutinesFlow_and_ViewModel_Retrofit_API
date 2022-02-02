package com.limuealimi.coroutinesflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.limuealimi.coroutinesflow.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var flow: Flow<Int>
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFlow()
        setupClick()
    }

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
    }
}