@file:Suppress("unused")

package com.king.android.ktx.core

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

inline fun <reified T> Context.intentOf(vararg pairs: Pair<String, Any?>) =
    intentOf<T>(bundleOf(*pairs))

inline fun <reified T> Context.intentOf(bundle: Bundle?) = intentOf(T::class.java, bundle)

fun Context.intentOf(cls: Class<*>, vararg pairs: Pair<String, Any?>) =
    intentOf(cls, bundleOf(*pairs))

fun Context.intentOf(cls: Class<*>, bundle: Bundle?) = Intent(this, cls).apply {
    bundle?.let {
        putExtras(it)
    }
}

//-----------------------------------

fun Context.checkPermission(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.checkPermissions(vararg permissions: String) = permissions.none {
    ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
}

//-----------------------------------

fun Context.getCompatMainExecutor() = ContextCompat.getMainExecutor(this)!!

fun Context.getCompatSystemService(serviceClass: Class<*>) =
    ContextCompat.getSystemService(this, serviceClass)

fun Context.getCompatSystemServiceName(serviceClass: Class<*>): String? =
    ContextCompat.getSystemServiceName(this, serviceClass)

fun Context.startCompatForegroundService(intent: Intent) =
    ContextCompat.startForegroundService(this, intent)

