package com.holderzone.library.composescaffold.util.ext

/**
 * @author terry
 * @date 18-9-17 下午1:36
 */

fun Array<*>?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

fun Array<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

inline fun <reified T> Array<T>.toNulls(): Array<T?> {
    @Suppress("UNCHECKED_CAST")
    return this as Array<T?>
}

fun <T> Array<T>.toStringArray(): Array<String> {
    return Array(size) { this[it].toString() }
}

inline fun <T, reified R> Array<T>.mapToArray(transform: (T) -> R): Array<R> {
    return Array(size) { transform(this[it]) }
}

inline fun <T> Array<T>.mapToIntArray(transform: (T) -> Int): IntArray {
    return IntArray(size) { transform(this[it]) }
}

inline fun <T, reified R> Array<T>.mapIndexedToArray(transform: (Int, T) -> R): Array<R> {
    return Array(size) { transform(it, this[it]) }
}

inline operator fun <reified T> Array<T>.minus(array: Array<out T>): Array<T> {
    return this.filterNot { it in array }.toTypedArray()
}

inline fun <reified R> LongArray.mapToArray(transform: (Long) -> R): Array<R> {
    return Array(size) { transform(this[it]) }
}

fun LongArray.toStringArray(): Array<String> {
    return Array(this.size) { idx -> this[idx].toString() }
}