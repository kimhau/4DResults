package com.kimhau.lotteryresult.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultDetail(
  @field:Json(name = "Name")  val name: String?,
  @field:Json(name = "DrawDate") val drawDate: String?,
  @field:Json(name = "_1") val firstPrize: List<String>?,
  @field:Json(name = "_2") val secondPrize: List<String>?,
  @field:Json(name = "_3") val thirdPrize: List<String>?,
  @field:Json(name = "_P") val specialPrizes: List<String>?,
  @field:Json(name = "C") val consolationPrizes: List<String>?
)