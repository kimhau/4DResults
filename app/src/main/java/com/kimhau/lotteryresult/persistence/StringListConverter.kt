package com.kimhau.lotteryresult.persistence

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

open class StringListConverter {
  @TypeConverter
  fun fromString(value: String): List<String>? {
    val listType = object : TypeToken<List<String>>() {}.type
    return Gson().fromJson<List<String>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<String>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
