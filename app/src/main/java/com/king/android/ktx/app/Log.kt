package com.king.android.ktx.app

import android.util.Log

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

fun log(priority: Int = Log.DEBUG, tag: String = "AndroidKTX", msg: String) =
    Log.println(priority, tag, msg)