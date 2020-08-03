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

package com.kimhau.lotteryresult.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultDetail(
  @field:Json(name = "Name") val name: String?,
  @field:Json(name = "DrawDate") val drawDate: String?,
  @field:Json(name = "_1") val firstPrize: List<String>?,
  @field:Json(name = "_2") val secondPrize: List<String>?,
  @field:Json(name = "_3") val thirdPrize: List<String>?,
  @field:Json(name = "_P") val specialPrizes: List<String>?,
  @field:Json(name = "C") val consolationPrizes: List<String>?
)
