package com.king.android.ktx.app

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Fragment.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), text, duration).show()
}