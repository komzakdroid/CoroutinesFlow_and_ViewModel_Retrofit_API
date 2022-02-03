# Practice with Coroutines Flow, ViewModel and get data with Retrofit from two API
 Project example for learn ViewModel, flow and Coroutines with fetching data with retrofit Api


# Show data with logd on MainActivity.class onCreate() method

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkHelper = NetworkHelper(this)
        postViewModel =
            ViewModelProvider(this, ViewModelFactory(networkHelper))[PostViewModel::class.java]

        postViewModel.getUserWithPost().observe(this, Observer {
            when (it) {
                is UserResource.Loading -> {
                    Log.d(TAG, "onCreate: Loading...")
                }
                is UserResource.Error -> {
                    Log.d(TAG, "onCreate: ${it.message}")
                }
                is UserResource.Success -> {
                    Log.d(TAG, "onCreate: ${it.userWithPost.userList}")
                    Log.d(TAG, "onCreate: ${it.userWithPost.postList}")
                }
            }
        })



# Used Libraries
  # ViewModelScope
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"

 # Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'

  # Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
