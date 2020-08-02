package com.kimhau.lotteryresult.persistence

import androidx.room.TypeConverter
import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.kimhau.lotteryresult.model.ResultDetail
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class ResultDetailConverter {

  private val moshi = Moshi.Builder().build()

  @TypeConverter
  fun fromString(value: String?): ResultDetail? {
    val adapter: JsonAdapter<ResultDetail> = moshi.adapter(ResultDetail::class.java)
    return if(value!=null)adapter.fromJson(value) else null
  }

  @TypeConverter
  fun fromResultDetail(type: ResultDetail?): String? {
    val adapter: JsonAdapter<ResultDetail> = moshi.adapter(ResultDetail::class.java)
    return if(type!=null)adapter.toJson(type) else null
  }
}
