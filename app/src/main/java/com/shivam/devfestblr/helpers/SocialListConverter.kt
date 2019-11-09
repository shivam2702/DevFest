package com.shivam.devfestblr.helpers

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.shivam.devfestblr.model.Social

class SocialListConverter {

    @TypeConverter
    fun listToJson(value: List<Social>): String {
        val type = object : TypeToken<List<Social>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Social> {
        val type = object : TypeToken<List<Social>>() {}.type
        return Gson().fromJson(value, type)
    }
}