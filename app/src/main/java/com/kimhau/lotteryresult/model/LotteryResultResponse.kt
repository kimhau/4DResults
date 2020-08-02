package com.kimhau.lotteryresult.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class LotteryResultResponse(
  @PrimaryKey var drawDate: String = "1900-01-01",
  @Json(name = "M") val magnum: ResultDetail?,
  @Json(name = "P") val damacai: ResultDetail?,
  @Json(name = "T") val toto: ResultDetail?,
  @Json(name = "S") val singaporePool: ResultDetail?
)