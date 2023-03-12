@file:Suppress("unused", "UNCHECKED_CAST")

package com.king.android.ktx.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.king.android.ktx.core.get
import com.king.android.ktx.core.intentOf

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

fun <T> Activity.lazyIntentExtra(key: String) = lazy {
    intent.extras?.get(key) as? T
}

fun <T> Activity.lazyIntentExtra(key: String, defaultValue: T) = lazy {
    intent.extras?.get(key, defaultValue) ?: defaultValue
}

//-----------------------------------

inline fun <reified T : Activity> Activity.startActivity(vararg pairs: Pair<String, Any?>) {
    startActivity(intentOf<T>(*pairs))
}

inline fun <reified T : Activity> Activity.startActivity(bundle: Bundle) {
    startActivity(intentOf<T>(bundle))
}

fun Activity.startActivity(cls: Class<*>, vararg pairs: Pair<String, Any?>) {
    startActivity(intentOf(cls, *pairs))
}

fun Activity.startActivity(cls: Class<*>, bundle: Bundle) {
    startActivity(intentOf(cls, bundle))
}

//-----------------------------------

fun Activity.finishWithResult(vararg pairs: Pair<String, Any?>) {
    finishWithResult(bundleOf(*pairs))
}

fun Activity.finishWithResult(bundle: Bundle) {
    setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
    finish()
}



