package com.oleksandrkarpiuk.recipemaster.database.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromStringToStringList(value: String): List<String> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.fromJson(value) ?: listOf()
    }

    @TypeConverter
    fun fromAnyList(value: List<Any>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Any::class.java)
        val adapter = moshi.adapter<List<Any>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromStringToAnyList(value: String): List<Any> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Any::class.java)
        val adapter = moshi.adapter<List<Any>>(type)
        return adapter.fromJson(value) ?: listOf()
    }

}