## Check & Request the permissions with Ask-Permissions-Android

### Download
#### 1.Add this in your root `build.gradle` at the end of repositories:
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
  
#### 2.Add this dependency in your app level `build.gradle`:
    dependencies {
        ...
        def ask_permissions_version = "1.0.0"
        implementation "com.github.memishood:android-ask-permissions:$ask_permissions_version"
    }

### 2. In your project:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            if (check(this, Manifest.permission.CAMERA)) {
                Log.d(TAG, "onCreate: permissions granted")
            } else {
                Log.d(TAG, "onCreate: permissions not granted")
                ask(this, Manifest.permission.CAMERA)
                Log.d(TAG, "onCreate: permissions asked")
            }
        }
    }

override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (check(activity = this, permissions = permissions)) {
            Log.d(TAG, "onRequestPermissionsResult: Permissions granted after dialog")
        } else {
            Log.d(TAG, "onRequestPermissionsResult: Permissions rejected after dialog")
        }
    }
```
