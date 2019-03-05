package com.ensar.tmdbkotlin.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class GenreTypeConverters {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Int> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Int>>() {}.type

        return gson.fromJson<List<Int>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Int>): String {
        return gson.toJson(someObjects)
    }

}