/*
 * Copyright 2020 kimhau (Kim Hau Wong)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
