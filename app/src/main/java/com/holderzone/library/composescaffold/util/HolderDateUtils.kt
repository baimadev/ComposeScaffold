package com.holderzone.library.composescaffold.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.text.SimpleDateFormat
import java.util.*

object HolderDateUtils {
    /**显示时间**/
    val week : MutableState<String> = mutableStateOf("")
    val times : MutableState<String> = mutableStateOf("")

    val day:MutableState<String> = mutableStateOf("")
    fun updateDateTime(){
        val date = Date()
        val dateFormat = SimpleDateFormat("HH:mm", Locale.CHINA)
        val dayFormat = SimpleDateFormat("MM月dd日", Locale.CHINA)
        //获取周
        week.value = SimpleDateFormat("E", Locale.CHINA).format(date)
        //获取当前时间
        times.value = dateFormat.format(date)
        //获取当前月份
        day.value = dayFormat.format(date)
    }
}