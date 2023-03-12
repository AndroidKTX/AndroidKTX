@file:Suppress("unused", "UNCHECKED_CAST")

package com.king.android.ktx.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

import com.king.android.ktx.core.get
import com.king.android.ktx.core.put

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

fun <T> argument(key: String? = null) = FragmentArgumentNullableProperty<T>(key)

fun <T> argument(key: String? = null, defaultValue: T) = FragmentArgumentProperty(key, defaultValue)


class FragmentArgumentProperty<V>(
    private val key: String?,
    private val defaultValue: V
) : ReadWriteProperty<Fragment, V> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        return thisRef.arguments?.get(key ?: property.name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: V) {
        val bundle = thisRef.arguments ?: Bundle().also { thisRef.arguments = it }
        bundle.put(key ?: property.name, value)
    }

}

class FragmentArgumentNullableProperty<V>(
    private val key: String?
) : ReadWriteProperty<Fragment, V?> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V? {
        return thisRef.arguments?.get(key ?: property.name) as? V
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: V?) {
        val bundle = thisRef.arguments ?: Bundle().also { thisRef.arguments = it }
        bundle.put(key ?: property.name, value)
    }
}



