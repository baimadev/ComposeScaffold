package com.holderzone.library.composescaffold.db

import androidx.room.TypeConverter
import com.holderzone.library.composescaffold.util.ext.MoshiUtils
import com.holderzone.library.composescaffold.util.ext.TypeToken
import java.lang.reflect.Type


/**
 * @ClassName : StringTypeConverter
 * @Description:
 * @Author: WuZhuoyu
 * @Date: 2021/6/9 12:11
 */

class StringTypeConverter {

    @TypeConverter
    fun stringToStringList(str: String?): List<String>? {
        if(str == null)return emptyList()
        val listType: Type = object : TypeToken<List<String>>() {}.type

        return MoshiUtils.fromJson(str, listType)
    }

    @TypeConverter
    fun stringListToString(list:List<String>): String {
        return MoshiUtils.toJson(list)
    }
}