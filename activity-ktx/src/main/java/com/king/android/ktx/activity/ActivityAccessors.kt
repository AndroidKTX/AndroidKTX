package com.king.android.ktx.activity

import android.app.Activity
import com.king.android.ktx.core.get
import com.king.android.ktx.core.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

fun <T> intentExtra(key: String? = null) = ActivityIntentExtraNullableProperty<T>(key)

fun <T> intentExtra(key: String? = null, defaultValue: T) =
    ActivityIntentExtraProperty(key, defaultValue)


class ActivityIntentExtraProperty<V>(
    private val key: String?,
    private val defaultValue: V
) : ReadWriteProperty<Activity, V> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): V {
        return thisRef.intent?.get(key ?: property.name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: V) {
        thisRef.intent.put(key ?: property.name, value)
    }

}

class ActivityIntentExtraNullableProperty<V>(
    private val key: String?
) : ReadWriteProperty<Activity, V?> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): V? {
        return thisRef.intent?.get(key ?: property.name)
    }

    override fun setValue(thisRef: Activity, property: KProperty<*>, value: V?) {
        thisRef.intent.put(key ?: property.name, value)
    }
}
