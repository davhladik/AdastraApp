package com.example.android.adastraapp.database

import androidx.room.TypeConverter

/**
 * Not used in the end, but its good to have it
 */
class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}