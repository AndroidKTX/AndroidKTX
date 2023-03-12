@file:Suppress("unused")

package com.king.android.ktx.core

import android.content.res.Resources
import android.util.TypedValue

/**
 * Dimension中主要定义了一些扩展属性和扩展函数，便于在不同单位之间转换
 *
 * Dimension 中定义的扩展属性主要是将不同单位的值转换为实际像素大小
 *
 * px：像素；如：10.px
 * dp：Density-independent pixel；如：10.dp；
 * sp：Scale-independent pixel；字体大小单位；如：10.sp；
 * pt：磅；1磅 = 1/72英寸；如：10.pt；
 * in：英寸；1英寸 = 25.4毫米；如：10.in；
 * mm：毫米；如：10.mm；最终转换为实际像素大小
 * vw：宽度百分比；如：1.vw 表示为宽度的 1%；
 * vh：高度百分比；如：1.vw 表示为高度的 1%；
 * vmax：宽高中较大值的百分比；如：1.vmax 表示为宽高中较大值的 1%；
 * vmin：宽高中较小值的百分比；如：1.vmax 表示为宽高中较小值的 1%；
 *
 * 其中的vw、vh、vmax、vmin 这些主要是参考了CSS中的一些单位概念
 *
 * Dimension 中定义的扩展函数主要是将实际像素值转换为其他单位值
 *
 * pxToDp() 将像素值转换为 Density-independent pixel
 * pxToSp() 将像素值转换为 Scale-independent pixel
 * pxToPt() 将像素值转换为磅值
 * pxToIn() 将像素值转换为英寸值
 * pxToMm() 将像素值转换为毫米值
 * pxToVw() 将像素值转换为宽度百分比值
 * pxToVh() 将像素值转换为高度百分比值
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

//-----------------------------------
/**
 * 当前资源；下面所有不同单位换算的值都是基于当前资源进行的；
 *
 * 当前资源默认获取的系统资源配置；如果你在适配时，有动态修改过App的密度相关配置；请记得更新当前资源为App的资源；
 */
var currentResources: Resources = Resources.getSystem()

/**
 * DisplayMetrics
 */
internal val displayMetrics get() = currentResources.displayMetrics

/**
 * 屏幕可用显示的宽度
 */
val screenWidth get() = displayMetrics.widthPixels

/**
 * 屏幕可用显示的高度
 */
val screenHeight get() = displayMetrics.heightPixels

//-----------------------------------
/**
 * px：像素；如：10.px
 */
val Int.px get() = this.toFloat().px

/**
 * px：像素；如：10.px
 */
val Double.px get() = this.toFloat().px

/**
 * px：像素；如：10.px
 */
val Float.px get() = applyDimension(TypedValue.COMPLEX_UNIT_PX, this)

/**
 * dp：Density-independent pixel；如：10.dp；最终转换为实际像素大小
 */
val Int.dp get() = this.toFloat().dp

/**
 * dp：Density-independent pixel；如：10.dp；最终转换为实际像素大小
 */
val Double.dp get() = this.toFloat().dp

/**
 * dp：Density-independent pixel；如：10.dp；最终转换为实际像素大小
 */
val Float.dp get() = applyDimension(TypedValue.COMPLEX_UNIT_DIP, this)

/**
 * sp：Scale-independent pixel；字体大小单位；如：10.sp；最终转换为实际像素大小
 */
val Int.sp get() = this.toFloat().sp

/**
 * sp：Scale-independent pixel；字体大小单位；如：10.sp；最终转换为实际像素大小
 */
val Double.sp get() = this.toFloat().sp

/**
 * sp：Scale-independent pixel；字体大小单位；如：10.sp；最终转换为实际像素大小
 */
val Float.sp get() = applyDimension(TypedValue.COMPLEX_UNIT_SP, this)

/**
 * pt：磅；1磅 = 1/72英寸；如：10.pt；最终转换为实际像素大小
 */
val Int.pt get() = this.toFloat().pt

/**
 * pt：磅；1磅 = 1/72英寸；如：10.pt；最终转换为实际像素大小
 */
val Double.pt get() = this.toFloat().pt

/**
 * pt：磅；1磅 = 1/72英寸；如：10.pt；最终转换为实际像素大小
 */
val Float.pt get() = applyDimension(TypedValue.COMPLEX_UNIT_PT, this)

/**
 * in：英寸；1英寸 = 25.4毫米；如：10.in；最终转换为实际像素大小
 */
val Int.`in` get() = this.toFloat().`in`

/**
 * in：英寸；1英寸 = 25.4毫米；如：10.in；最终转换为实际像素大小
 */
val Double.`in` get() = this.toFloat().`in`

/**
 * in：英寸；1英寸 = 25.4毫米；如：10.in；最终转换为实际像素大小
 */
val Float.`in` get() = applyDimension(TypedValue.COMPLEX_UNIT_IN, this)

/**
 * mm：毫米；如：10.mm；最终转换为实际像素大小
 */
val Int.mm get() = this.toFloat().mm

/**
 * mm：毫米；如：10.mm；最终转换为实际像素大小
 */
val Double.mm get() = this.toFloat().mm

/**
 * mm：毫米；如：10.mm；最终转换为实际像素大小
 */
val Float.mm get() = applyDimension(TypedValue.COMPLEX_UNIT_MM, this)

//-----------------------------------

/**
 * vw：宽度百分比；如：1.vw 表示为宽度的 1%；最终转换为实际像素大小
 */
val Int.vw get() = this.toFloat().vw

/**
 * vw：宽度百分比；如：1.vw 表示为宽度的 1%；最终转换为实际像素大小
 */
val Double.vw get() = this.toFloat().vw

/**
 * vw：宽度百分比；如：1.vw 表示为宽度的 1%；最终转换为实际像素大小
 */
val Float.vw get() = screenWidth.div(100F).times(this)

/**
 * vh：高度百分比；如：1.vw 表示为高度的 1%；最终转换为实际像素大小
 */
val Int.vh get() = this.toFloat().vh

/**
 * vh：高度百分比；如：1.vw 表示为高度的 1%；最终转换为实际像素大小
 */
val Double.vh get() = this.toFloat().vh

/**
 * vh：高度百分比；如：1.vw 表示为高度的 1%；最终转换为实际像素大小
 */
val Float.vh get() = screenHeight.div(100F).times(this)

/**
 * vmax：宽高中较大值的百分比；如：1.vmax 表示为宽高中较大值的 1%；最终转换为实际像素大小
 */
val Int.vmax get() = this.toFloat().vmax

/**
 * vmax：宽高中较大值的百分比；如：1.vmax 表示为宽高中较大值的 1%；最终转换为实际像素大小
 */
val Double.vmax get() = this.toFloat().vmax

/**
 * vmax：宽高中较大值的百分比；如：1.vmax 表示为宽高中较大值的 1%；最终转换为实际像素大小
 */
val Float.vmax get() = screenWidth.coerceAtLeast(screenHeight).div(100F).times(this)

/**
 * vmin：宽高中较小值的百分比；如：1.vmax 表示为宽高中较小值的 1%；最终转换为实际像素大小
 */
val Int.vmin get() = this.toFloat().vmin

/**
 * vmin：宽高中较小值的百分比；如：1.vmax 表示为宽高中较小值的 1%；最终转换为实际像素大小
 */
val Double.vmin get() = this.toFloat().vmin

/**
 * vmin：宽高中较小值的百分比；如：1.vmax 表示为宽高中较小值的 1%；最终转换为实际像素大小
 */
val Float.vmin get() = screenWidth.coerceAtMost(screenHeight).div(100F).times(this)

/**
 * 将不同单位的值转换为实际像素大小
 */
fun applyDimension(unit: Int, value: Float): Float {
    return TypedValue.applyDimension(unit, value, displayMetrics)
}

/**
 * px to dp；将像素值转换为 Density-independent pixel
 */
fun Int.pxToDp() = this.toFloat().pxToDp()

/**
 * px to dp；将像素值转换为 Density-independent pixel
 */
fun Double.pxToDp() = this.toFloat().pxToDp()

/**
 * px to dp；将像素值转换为 Density-independent pixel
 */
fun Float.pxToDp() = this.div(displayMetrics.density)

/**
 * px to sp；将像素值转换为 Scale-independent pixel
 */
fun Int.pxToSp() = this.toFloat().pxToSp()

/**
 * px to sp；将像素值转换为 Scale-independent pixel
 */
fun Double.pxToSp() = this.toFloat().pxToSp()

/**
 * px to sp；将像素值转换为 scale-independent pixel
 */
fun Float.pxToSp() = this.div(displayMetrics.scaledDensity)

/**
 * px to pt；将像素值转换为磅值
 */
fun Int.pxToPt() = this.toFloat().pxToPt()

/**
 * px to pt；将像素值转换为磅值
 */
fun Double.pxToPt() = this.toFloat().pxToPt()

/**
 * px to pt；将像素值转换为磅值
 */
fun Float.pxToPt() = this.div(displayMetrics.xdpi).div(1.0f / 72f)

/**
 * px to in；将像素值转换为英寸值
 */
fun Int.pxToIn() = this.toFloat().pxToIn()

/**
 * px to in；将像素值转换为英寸值
 */
fun Double.pxToIn() = this.toFloat().pxToIn()

/**
 * px to in；将像素值转换为英寸值
 */
fun Float.pxToIn() = this.div(displayMetrics.xdpi)

/**
 * px to mm；将像素值转换为毫米值
 */
fun Int.pxToMm() = this.toFloat().pxToMm()

/**
 * px to mm；将像素值转换为毫米值
 */
fun Double.pxToMm() = this.toFloat().pxToMm()

/**
 * px to mm；将像素值转换为毫米值
 */
fun Float.pxToMm() = this.div(displayMetrics.xdpi).div(1.0f / 25.4f)

/**
 * px to vw；将像素值转换为宽度百分比值
 */
fun Int.pxToVw() = this.toFloat().pxToVw()

/**
 * px to vw；将像素值转换为宽度百分比值
 */
fun Double.pxToVw() = this.toFloat().pxToVw()

/**
 * px to vw；将像素值转换为宽度百分比值
 */
fun Float.pxToVw() = this.times(100F).div(screenWidth)

/**
 * px to vh；将像素值转换为高度百分比值
 */
fun Int.pxToVh() = this.toFloat().pxToVh()

/**
 * px to vh；将像素值转换为高度百分比值
 */
fun Double.pxToVh() = this.toFloat().pxToVh()

/**
 * px to vh；将像素值转换为高度百分比值
 */
fun Float.pxToVh() = this.times(100F).div(screenHeight)
