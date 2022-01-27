package com.holderzone.library.composescaffold.util.ext

import android.util.Base64
import java.nio.charset.Charset
import java.security.MessageDigest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author terry
 * @date 2018/08/25 下午17:00
 */

fun String.dateInFormat(format: String): Date? {
    val dateFormat = SimpleDateFormat(format, Locale.US)
    var parsedDate: Date? = null
    try {
        parsedDate = dateFormat.parse(this)
    } catch (ignored: ParseException) {
        ignored.printStackTrace()
    }
    return parsedDate
}

fun String?.orDefault() = this ?: "undefined"

fun String.isNumeric() = matches(Regex("\\d+"))

/**
 * 字符串打马赛克 例如：13297713450 => 132****3450
 *
 * @param char          打码显示的字符
 * @param startPosition 起始位置
 * @param endPosition   结束未知
 */
fun String.mosaic(char: Char = '*', startPosition: Int = 1, endPosition: Int = length): String =
    StringBuilder().also { stringBuilder -> (0 until length).forEach { i -> stringBuilder.append(if (i in startPosition..endPosition) char else this@mosaic[i]) } }.toString()

fun String.base64Encode(
    charset: Charset = Charsets.UTF_8,
    base64Flag: Int = Base64.NO_WRAP
): String =
    Base64.encodeToString(this.toByteArray(charset), base64Flag)

fun String.base64Decode(
    charset: Charset = Charsets.UTF_8,
    base64Flag: Int = Base64.NO_WRAP
): String =
    String(Base64.decode(this, base64Flag), charset)

fun String.md5(charset: Charset = Charsets.UTF_8, useUpper: Boolean = true) =
    MessageDigest.getInstance("MD5").digest(this.toByteArray(charset)).joinToString("") {
        String.format(
            if (useUpper) "%02X" else "%02x",
            it
        )
    }

fun String.random(length: Int = 10, random: Random = Random(System.currentTimeMillis())): String =
    (0 until length).map { this[random.nextInt(this.length)] }.joinToString("")

fun String?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}