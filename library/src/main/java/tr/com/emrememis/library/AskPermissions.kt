package tr.com.emrememis.library

import android.app.Activity
import android.content.pm.PackageManager
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * @author memishood {https://github.com/memishood}
 * 18.02.2021
 */

object AskPermissions {
    private const val REQUEST_CODE = 7124

    /**
     * @param activity {We need a context for request the permissions}
     * @param permissions {permissions list}
     */
    fun ask(
        @NonNull activity: Activity,
        @NonNull vararg permissions: String
    ) {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(activity.applicationContext, it) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    activity,
                    permissions,
                    REQUEST_CODE
                )
            }
        }
    }

    /**
     * @param activity {We need a context for check the permissions}
     * @param permissions {permissions list}
     */
    fun check(
        @NonNull activity: Activity,
        @NonNull vararg permissions: String
    ): Boolean {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(activity.applicationContext, it)
                != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * @param fragment {We need a context for request the permissions}
     * @param permissions {permissions list}
     */
    fun ask(
        @NonNull fragment: Fragment?,
        @NonNull vararg permissions: String
    ) {
        val activity = fragment?.activity ?: return
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(activity.applicationContext, it) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
            }
        }
    }

    /**
     * @param fragment {We need a context for check the permissions}
     * @param permissions {permissions list}
     */
    fun check(
        @NonNull fragment: Fragment?,
        @NonNull vararg permissions: String
    ): Boolean {
        permissions.forEach {
            val activity = fragment?.activity ?: return false
            if (ContextCompat.checkSelfPermission(activity.applicationContext, it)
                != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}