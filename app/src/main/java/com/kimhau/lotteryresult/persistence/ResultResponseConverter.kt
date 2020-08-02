package com.kimhau.lotteryresult.persistence

import androidx.room.TypeConverter
import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.kimhau.lotteryresult.model.ResultDetail
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class ResultResponseConverter {

  private val moshi = Moshi.Builder().build()

  @TypeConverter
  fun fromString(value: String): LotteryResultResponse? {
    val adapter: JsonAdapter<LotteryResultResponse> = moshi.adapter(LotteryResultResponse::class.java)
    return adapter.fromJson(value)
  }

  @TypeConverter
  fun fromResultResponse(type: LotteryResultResponse): String {
    val adapter: JsonAdapter<LotteryResultResponse> = moshi.adapter(LotteryResultResponse::class.java)
    return adapter.toJson(type)
  }

}
