# Practice with Coroutines Flow, ViewModel and get data with Retrofit from two APIs
 Project example for learn ViewModel, flow and Coroutines with fetching data with retrofit from two Apis


# Show data with logd on MainActivity.class onCreate() method

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
            }
# Note 

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 *
 *
 * Note that only one observer is going to be notified of changes.
 */


            class SingleLiveEvent<T> : MutableLiveData<T>() {
  
            private val mPending = AtomicBoolean(false)

            @MainThread
            override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

             if (hasActiveObservers()) {
               Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
              }

              // Observe the internal MutableLiveData
                super.observe(owner, Observer { t ->
                  if (mPending.compareAndSet(true, false)) {
                   observer.onChanged(t)
                     }
                  })
                 }

                 @MainThread
                  override fun setValue(t: T?) {
                  mPending.set(true)
                  super.setValue(t)
                  }

                 /**
                   * Used for cases where T is Void, to make calls cleaner.
                   */
       
                   @MainThread
                    fun call() {
                    value = null
                      }

                     companion object {

                      private const val TAG = "SingleLiveEvent"
                       }
                      }

  

# Used Libraries
  # ViewModelScope
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"

 # Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'

  # Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
