package com.holderzone.library.composescaffold.util.ext

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author terry
 * @date 2018/08/19 下午13:22
 */
object DelegatesExt {

    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}

private class NotNullSingleValueVar<T> : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} " + "not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized ")
    }
}