@file:Suppress("unused", "UNCHECKED_CAST")

package com.king.android.ktx.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.king.android.ktx.core.intentOf

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

fun <T> Fragment.lazyArgument(key: String) = lazy {
    arguments?.get(key) as? T
}

fun <T> Fragment.lazyArgument(key: String, defaultValue: T) = lazy {
    arguments?.get(key) as? T ?: defaultValue
}

//-----------------------------------

fun <T> Fragment.intentExtra(key: String) = lazy {
    activity?.intent?.extras?.get(key) as? T
}

fun <T> Fragment.intentExtra(key: String, defaultValue: T) = lazy {
    activity?.intent?.extras?.get(key) as? T ?: defaultValue
}

//-----------------------------------

inline fun <reified T : Activity> Fragment.startActivity(vararg pairs: Pair<String, Any?>) {
    startActivity(requireContext().intentOf<T>(*pairs))
}

inline fun <reified T : Activity> Fragment.startActivity(bundle: Bundle) {
    startActivity(requireContext().intentOf<T>(bundle))
}

fun Fragment.startActivity(cls: Class<*>, vararg pairs: Pair<String, Any?>) {
    startActivity(requireContext().intentOf(cls, *pairs))
}

fun Fragment.startActivity(cls: Class<*>, bundle: Bundle) {
    startActivity(requireContext().intentOf(cls, bundle))
}

//-----------------------------------

fun Fragment.finish() {
    requireActivity().finish()
}

fun Fragment.finishWithResult(vararg pairs: Pair<String, Any?>) {
    finishWithResult(bundleOf(*pairs))
}

fun Fragment.finishWithResult(bundle: Bundle) {
    requireActivity().run {
        setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
        finish()
    }
}
