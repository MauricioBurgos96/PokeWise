package com.mauricioburgos.pokeyellow.data.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mauricioburgos.pokeyellow.domain.TypesItem
import java.util.*

class TypeConverters {

    @TypeConverter
    fun stringToTypesItem(data: String?): List<TypesItem> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<TypesItem>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun typesItemToString(genreIds: List<TypesItem>?): String {
        if (genreIds == null) {
            return Gson().toJson(Collections.emptyList<Int>())
        }
        return Gson().toJson(genreIds)
    }
}