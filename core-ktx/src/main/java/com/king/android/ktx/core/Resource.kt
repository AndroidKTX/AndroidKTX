@file:Suppress("unused")

package com.king.android.ktx.core

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------

@ColorInt
fun Context.getCompatColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Context.getCompatColorStateList(@ColorRes id: Int): ColorStateList? =
    ContextCompat.getColorStateList(this, id)

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? =
    ContextCompat.getDrawable(this, id)

fun Context.getCompatColor(@ColorRes id: Int, theme: Resources.Theme) =
    ResourcesCompat.getColor(resources, id, theme)

fun Context.getCompatColorStateList(
    @ColorRes id: Int,
    theme: Resources.Theme
): ColorStateList? =
    ResourcesCompat.getColorStateList(resources, id, theme)

fun Context.getCompatFont(@FontRes id: Int): Typeface? = ResourcesCompat.getFont(this, id)

fun Context.getCompatCachedFont(@FontRes id: Int): Typeface? =
    ResourcesCompat.getCachedFont(this, id)

//-----------------------------------

fun Context.getDimension(@DimenRes id: Int) = resources.getDimension(id)

fun Context.getInt(@IntegerRes id: Int) = resources.getInteger(id)

fun Context.getIntArray(@ArrayRes id: Int) = resources.getIntArray(id)

fun Context.getBoolean(@BoolRes id: Int) = resources.getBoolean(id)

fun Context.getAnimation(@AnimatorRes @AnimRes id: Int) = resources.getAnimation(id)

fun Context.getText(@StringRes id: Int, defValue: CharSequence? = null): CharSequence? =
    resources.getText(id, defValue)

fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)

fun Context.getTextArray(@ArrayRes id: Int): Array<CharSequence> = resources.getTextArray(id)

fun Context.getFloat(@DimenRes id: Int) = ResourcesCompat.getFloat(resources, id)

fun Context.getXml(@XmlRes id: Int) = resources.getXml(id)


