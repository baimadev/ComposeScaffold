@file:Suppress("NOTHING_TO_INLINE")

package com.holderzone.library.composescaffold.util.ext

import java.text.NumberFormat
import java.util.*

/**
 * @author terry
 * @date 18-9-17 下午1:58
 */

inline fun Boolean?.orDefault(): Boolean = this ?: false

inline fun Byte?.orDefault(): Byte = this ?: 0

inline fun Short?.orDefault(): Short = this ?: 0

inline fun Int?.orDefault(): Int = this ?: 0

inline fun Long?.orDefault(): Long = this ?: 0

inline fun Float?.orDefault(): Float = this ?: 0f

inline fun Double?.orDefault(): Double = this ?: 0.00

inline fun String?.toLongOr(def: Long) = try {
    this?.toLong() ?: def
} catch (e: NumberFormatException) {
    def
}

inline fun String?.toIntOr(def: Int) = try {
    this?.toInt() ?: def
} catch (e: NumberFormatException) {
    def
}

inline fun String?.toDoubleOr(def: Double) = try {
    this?.toDouble() ?: def
} catch (e: NumberFormatException) {
    def
}

inline fun Int.coerceInOr(range: ClosedRange<Int>, def: Int): Int {
    if (range.isEmpty()) return def
    return coerceIn(range)
}

inline fun String?.toBooleanOr(def: Boolean) = try {
    this?.toBoolean() ?: def
} catch (e: NumberFormatException) {
    def
}

/**
 * Convenience method checking int flags
 */
operator fun Int.contains(i: Int): Boolean = (this and i) == i

/**
 * Convenience method checking long flags
 */
operator fun Long.contains(i: Long): Boolean = (this and i) == i

fun Number.toLocalizedString(locale: Locale = Locale.getDefault()): String {
    val nf = NumberFormat.getInstance(locale)
    return nf.format(this)
}

val Int.nextPowerOf2: Int
    get() {
        var n = this
        if (n <= 0 || n > 1 shl 30) throw IllegalArgumentException("n is invalid: " + n)
        n -= 1
        n = n or (n shr 16)
        n = n or (n shr 8)
        n = n or (n shr 4)
        n = n or (n shr 2)
        n = n or (n shr 1)
        return n + 1
    }


fun Int.division(number:Int):Int{

    val yu = this % number
    var result = this / number
    if (yu > 0){
       result += 1
    }
    return result
}