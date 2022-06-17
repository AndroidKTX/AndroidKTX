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
 *
 *
 */

//-----------------------------------

@ColorInt
inline fun Context.getCompatColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

inline fun Context.getCompatColorStateList(@ColorRes id: Int): ColorStateList? =
    ContextCompat.getColorStateList(this, id)

inline fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? =
    ContextCompat.getDrawable(this, id)

inline fun Context.getCompatColor(@ColorRes id: Int, theme: Resources.Theme) =
    ResourcesCompat.getColor(resources, id, theme)

inline fun Context.getCompatColorStateList(
    @ColorRes id: Int,
    theme: Resources.Theme
): ColorStateList? =
    ResourcesCompat.getColorStateList(resources, id, theme)

inline fun Context.getCompatFont(@FontRes id: Int): Typeface? = ResourcesCompat.getFont(this, id)

inline fun Context.getCompatCachedFont(@FontRes id: Int): Typeface? =
    ResourcesCompat.getCachedFont(this, id)

//-----------------------------------

inline fun Context.getDimension(@DimenRes id: Int) = resources.getDimension(id)

inline fun Context.getInt(@IntegerRes id: Int) = resources.getInteger(id)

inline fun Context.getIntArray(@ArrayRes id: Int) = resources.getIntArray(id)

inline fun Context.getBoolean(@BoolRes id: Int) = resources.getBoolean(id)

inline fun Context.getAnimation(@AnimatorRes @AnimRes id: Int) = resources.getAnimation(id)

inline fun Context.getText(@StringRes id: Int, defValue: CharSequence? = null): CharSequence? =
    resources.getText(id, defValue)

inline fun Context.getStringArray(@ArrayRes id: Int): Array<String> = resources.getStringArray(id)

inline fun Context.getTextArray(@ArrayRes id: Int): Array<CharSequence> = resources.getTextArray(id)

inline fun Context.getFloat(@DimenRes id: Int) = ResourcesCompat.getFloat(resources, id)

inline fun Context.getXml(@XmlRes id: Int) = resources.getXml(id)



