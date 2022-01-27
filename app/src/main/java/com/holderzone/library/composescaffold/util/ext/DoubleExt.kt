package com.holderzone.library.composescaffold.util.ext

import java.math.BigDecimal
import java.text.DecimalFormat


/**
 * 对Double类型等浮点数的一些扩展
 */

/**
 * 或者函数，daouble类型转换指定保留指定位数的小数
 * [scale] 需要保留的位数,四色五入
 */
internal fun Double.toScale(scale: Int): Double {
    return BigDecimal.valueOf(this).setScale(scale, BigDecimal.ROUND_HALF_UP).toDouble()
}

/**
 * 保留小数的函数，daouble类型转换指定保留指定位数的小数，并且舍去多余部分
 * [scale] 需要保留的位数,其余位数舍去
 */
internal fun Double.toScaleUnnecessary(scale: Int): Double {
    return BigDecimal.valueOf(this).setScale(scale, BigDecimal.ROUND_DOWN).toDouble()
}

/**
 * 去掉科学计数法并且转换为String
 */
fun Double.notGrouping(): String {
    val NF = java.text.NumberFormat.getInstance()
    NF.isGroupingUsed = false //去掉科学计数法显示
    return NF.format(this)
}

fun Int.notGrouping(): String {
    val NF = java.text.NumberFormat.getInstance()
    NF.isGroupingUsed = false //去掉科学计数法显示
    return NF.format(this)
}


/**
 * 重量的显示方式
 * 保留后三位有效小数
 */
fun Double.toWeight(): String {
    return if (this.floor() == this)
        this.toInt().toString()
    else
        this.toScale(3).toString()
}


fun Double.toMoney(): String {
    return if (this.floor() == this)
        this.toInt().toString()
    else
        this.toScale(2).notGrouping()
}


/**
 * 提供精确的加法运算。
 *
 * @param this 被加数
 * @param d2 加数
 * @return 两个参数的和
 */

internal fun Double.add(d2: Number): Double {
    val b1 = BigDecimal(java.lang.Double.toString(this))
    val b2 = BigDecimal(java.lang.Double.toString(d2.toDouble()))
    return b1.add(b2).toDouble()
}

/**
 * 提供精确的减法运算。
 *
 * @param this 被减数
 * @param d2 减数
 * @return 两个参数的差
 */
internal fun Double.sub(d2: Number): Double {
    val b1 = BigDecimal(java.lang.Double.toString(this))
    val b2 = BigDecimal(java.lang.Double.toString(d2.toDouble()))
    return b1.subtract(b2).toDouble()
}

/**
 * 提供精确的乘法运算。
 *
 * @param this 被乘数
 * @param d2 乘数
 * @return 两个参数的积
 */
internal fun Double.mul(d2: Number): Double {
    val b1 = BigDecimal(java.lang.Double.toString(this))
    val b2 = BigDecimal(java.lang.Double.toString(d2.toDouble()))
    return b1.multiply(b2).toDouble()
}


/**
 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
 * 定精度，以后的数字四舍五入。
 *
 * @param this    被除数
 * @param d2    除数
 * @param scale 表示表示需要精确到小数点以后几位。
 * @return 两个参数的商
 */
@JvmOverloads
internal fun Double.div(d2: Number, scale: Int = 10): Double {
    if (scale < 0) {
        throw IllegalArgumentException("The scale must be a positive integer or zero")
    }
    val b1 = BigDecimal(java.lang.Double.toString(this))
    val b2 = BigDecimal(java.lang.Double.toString(d2.toDouble()))
    return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
}

/**
 * 提供精确的小数位四舍五入处理。
 *
 * @param scale 小数点后保留几位
 * @return 四舍五入后的结果
 */
internal fun Double.round(scale: Int): Double {
    if (scale < 0) {
        throw IllegalArgumentException(
            "The scale must be a positive integer or zero"
        )
    }
    val b = BigDecimal(java.lang.Double.toString(this))
    val one = BigDecimal("1")
    return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toDouble()
}

/**
 * 去掉double后面多余的0
 *
 * @param scale 小数点后保留几位
 * @return 四舍五入后的结果
 */
internal fun Double.stripTrailingZeros(): String {
    if (0.0 == this) {
        return "0"
    }
    return BigDecimal(this.toString()).stripTrailingZeros().toPlainString()
}

/**
 * 保留小数点后几位，不足的用0补
 *
 * @param scale 小数点后保留几位
 * @return 四舍五入后的结果
 */
internal fun Double.roundByScale(scale: Int): String {
    if (scale < 0) {
        throw IllegalArgumentException(
            "The   scale   must   be   a   positive   integer   or   zero"
        )
    }
    if (scale == 0) {
        return DecimalFormat("0").format(this)
    }
    var formatStr = "0."
    for (i in 0 until scale) {
        formatStr = formatStr + "0"
    }
    return DecimalFormat(formatStr).format(this)
}

/**
 * 保留小数点后几位，不足的用0补
 *
 * @param scale 小数点后保留几位
 * @return 四舍五入后的结果
 */
internal fun Double.isEqualZero(): Boolean {
    if (0.0 == this) {
        return true
    }
    return false
}