package com.holderzone.library.composescaffold.util.ext

import java.text.Normalizer


/**
 * @author terry
 * @date 2018/09/09 下午5:37
 */

fun CharSequence.strToInt(): Int = toString().toInt()

fun CharSequence.strToLong(): Long = toString().toLong()

fun CharSequence.strToFloat(): Float = toString().toFloat()

fun CharSequence.strToDouble(): Double = toString().toDouble()

fun CharSequence.strToIntOrNull(): Int? = toString().toIntOrNull()

fun CharSequence.strToLongOrNull(): Long? = toString().toLongOrNull()

fun CharSequence.strToFloatOrNull(): Float? = toString().toFloatOrNull()

fun CharSequence.strToDoubleOrNull(): Double? = toString().toDoubleOrNull()

fun CharSequence.rmbToInt(): Int = toString().substring(1).strToInt()

fun CharSequence.rmbToFloat(): Float = toString().substring(1).toFloat()

fun CharSequence.rmbToDouble(): Double = toString().substring(1).toDouble()

fun CharSequence.rmbToIntOrNull(): Int? = toString().substring(1).toIntOrNull()

fun CharSequence.rmbToFloatOrNull(): Float? = toString().substring(1).toFloatOrNull()

fun CharSequence.rmbToDoubleOrNull(): Double? = toString().substring(1).toDoubleOrNull()

fun CharSequence.appendTo(sb: StringBuilder) {
    sb.append(this)
}

operator fun CharSequence.times(n: Int): String = repeat(n)

fun CharSequence.normalized(form: Normalizer.Form): String = Normalizer.normalize(this, form)