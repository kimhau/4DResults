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
import com.kimhau.lotteryresult.model.ResultDetail
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

open class ResultDetailConverter {

  private val moshi = Moshi.Builder().build()

  @TypeConverter
  fun fromString(value: String?): ResultDetail? {
    val adapter: JsonAdapter<ResultDetail> = moshi.adapter(ResultDetail::class.java)
    return if (value != null)adapter.fromJson(value) else null
  }

  @TypeConverter
  fun fromResultDetail(type: ResultDetail?): String? {
    val adapter: JsonAdapter<ResultDetail> = moshi.adapter(ResultDetail::class.java)
    return if (type != null)adapter.toJson(type) else null
  }
}
