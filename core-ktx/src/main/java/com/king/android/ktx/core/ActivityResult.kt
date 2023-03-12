@file:Suppress("unused")

package com.king.android.ktx.core

import android.app.Activity
import androidx.activity.result.ActivityResult

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

fun ActivityResult.isResultOk() = this.resultCode == Activity.RESULT_OK

fun ActivityResult.isResultCanceled() = this.resultCode == Activity.RESULT_CANCELED