package com.google.android.apps.muzei.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import java.util.*

object PermissionsUtil {
    fun hasPermissions(context: Context, allPermissionNeeded: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && allPermissionNeeded != null) {
            for (permissionNeeded in allPermissionNeeded) {
                if (ActivityCompat.checkSelfPermission(context, permissionNeeded!!) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    fun hasPermissions(allPermissionNeeded: Array<String?>, permissions: Array<String>, grantResults: IntArray): Boolean {
        val PermissionsMap: MutableMap<String, Int> = HashMap()
        for (i in permissions.indices) {
            PermissionsMap[permissions[i]] = grantResults[i]
        }
        for (permissionNeeded in allPermissionNeeded) {
            if (PermissionsMap[permissionNeeded] != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}