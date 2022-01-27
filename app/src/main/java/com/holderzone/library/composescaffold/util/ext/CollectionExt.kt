package com.holderzone.library.composescaffold.util.ext

/**
 * @author terry
 * @date 18-9-17 下午1:44
 */
fun Collection<*>?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

fun Collection<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

fun <T> MutableCollection<T>.addAllEnhanced(
    collection: Collection<T>,
    ignoreDuplicates: Boolean
): Boolean {
    return if (ignoreDuplicates) {
        addAll(collection.filter { it !in this })
    } else {
        addAll(collection)
    }
}

fun <T> Collection<T>.addAllTo(collection: MutableCollection<in T>): Boolean {
    return collection.addAll(this)
}

fun <T> Array<T>.addAllTo(collection: MutableCollection<in T>): Boolean {
    return collection.addAll(this)
}

fun <E> Collection<E>?.nullableContentEquals(other: Collection<E>?): Boolean {
    if (this == null) return other.isNullOrEmpty()
    return contentEquals(other!!)
}

fun <E> Collection<E>.contentEquals(other: Collection<E>): Boolean {
    if (this === other) return true
    if (this.size != other.size) return false
    return this.containsAll(other) && other.containsAll(this)
}

inline fun <reified T> List<T>.subArray(range: IntRange): Array<T> {
    return Array(range.count()) {
        this[range.start + it]
    }
}

fun <T> T.addTo(collection: MutableCollection<T>): Boolean {
    return collection.add(this)
}

inline fun <T : Any, reified R : Any> Collection<T>.mapToArray(transform: (T) -> R): Array<R> {
    return map(transform).toTypedArray()
}

inline fun <T : Any, reified R : Any> List<T>.mapToArray(transform: (T) -> R): Array<R> {
    return Array(size) { transform(this[it]) }
}

fun <T:Any> List<T>.splitList(num:Int):List<List<T>>{
    val result = arrayListOf<List<T>>()

    if (this.isNotEmpty()  && num > 0) {
        if (this.size <= num) {
            // 源List元素数量小于等于目标分组数量
            result.add(this)
        } else {
            // 计算拆分后list数量
            val splitNum = if (this.size % num == 0) this.size / num else this.size / num + 1

            var value: List<T>

            for (i in 0 until splitNum) {
                value = if (i < splitNum - 1) {
                    this.subList(i * num, (i + 1) * num)
                } else {
                    // 最后一组
                    this.subList(i * num, this.size)
                }
                result.add(value)
            }
        }
    }

    return result

}