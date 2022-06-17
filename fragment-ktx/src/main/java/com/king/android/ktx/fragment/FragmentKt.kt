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

inline fun <T> Fragment.lazyArgument(key: String) = lazy {
    arguments?.get(key) as? T?
}

inline fun <T> Fragment.lazyArgument(key: String, defaultValue: T) = lazy {
    arguments?.get(key) as? T ?: defaultValue
}


//-----------------------------------

inline fun <T> Fragment.intentExtra(key: String) = lazy {
    activity?.intent?.extras?.get(key) as? T
}

inline fun <T> Fragment.intentExtra(key: String, defaultValue: T) = lazy {
    activity?.intent?.extras?.get(key) as? T ?: defaultValue
}

//-----------------------------------

inline fun <reified T : Activity> Fragment.startActivity(vararg pairs: Pair<String, Any?>) {
    startActivity(requireContext().intentOf<T>(*pairs))
}

inline fun <reified T : Activity> Fragment.startActivity(bundle: Bundle) {
    startActivity(requireContext().intentOf<T>(bundle))
}

inline fun Fragment.startActivity(cls: Class<*>, vararg pairs: Pair<String, Any?>) {
    startActivity(requireContext().intentOf(cls, *pairs))
}

inline fun Fragment.startActivity(cls: Class<*>, bundle: Bundle) {
    startActivity(requireContext().intentOf(cls, bundle))
}

//-----------------------------------

inline fun Fragment.finish() {
    requireActivity().finish()
}

inline fun Fragment.finishWithResult(vararg pairs: Pair<String, Any?>) {
    finishWithResult(bundleOf(*pairs))
}

inline fun Fragment.finishWithResult(bundle: Bundle) {
    requireActivity().run {
        setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
        finish()
    }
}
