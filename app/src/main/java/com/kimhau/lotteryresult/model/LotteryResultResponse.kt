package com.kimhau.lotteryresult.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
data class LotteryResultResponse(
  @Json(name = "M") val magnum: ResultDetail?,
  @Json(name = "P") val damacai: ResultDetail?,
  @Json(name = "T") val toto: ResultDetail?,
  @Json(name = "S") val singaporePool: ResultDetail?
)
inline fun <reified T> T.toJson(): String = Moshi.Builder().build()
  .adapter(T::class.java)
  .toJson(this)