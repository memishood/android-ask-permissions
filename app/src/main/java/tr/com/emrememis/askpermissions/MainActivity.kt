package tr.com.emrememis.askpermissions

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tr.com.emrememis.askpermissions.databinding.ActivityMainBinding
import tr.com.emrememis.library.AskPermissions.ask
import tr.com.emrememis.library.AskPermissions.check

class MainActivity : AppCompatActivity() {
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

    companion object {
        private const val TAG = "MainActivity"
    }
}