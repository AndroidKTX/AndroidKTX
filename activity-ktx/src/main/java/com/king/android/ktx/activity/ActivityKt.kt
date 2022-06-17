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

inline fun <T> Activity.lazyIntentExtra(key: String) = lazy {
    intent.extras?.get(key) as? T ?: null
}

inline fun <T> Activity.lazyIntentExtra(key: String, defaultValue: T) = lazy {
    intent.extras?.get(key, defaultValue) ?: defaultValue
}

//-----------------------------------

inline fun <reified T : Activity> Activity.startActivity(vararg pairs: Pair<String, Any?>) {
    startActivity(intentOf<T>(*pairs))
}

inline fun <reified T : Activity> Activity.startActivity(bundle: Bundle) {
    startActivity(intentOf<T>(bundle))
}

inline fun Activity.startActivity(cls: Class<*>, vararg pairs: Pair<String, Any?>) {
    startActivity(intentOf(cls, *pairs))
}

inline fun Activity.startActivity(cls: Class<*>, bundle: Bundle) {
    startActivity(intentOf(cls, bundle))
}

//-----------------------------------

inline fun Activity.finishWithResult(vararg pairs: Pair<String, Any?>) {
    finishWithResult(bundleOf(*pairs))
}

inline fun Activity.finishWithResult(bundle: Bundle) {
    setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
    finish()
}



