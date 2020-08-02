package com.kimhau.lotteryresult.network

import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface LotteryResultService {

  @Headers("referer: https://4dyes.com/en/past-result")
  @GET("getLiveResult.php")
  suspend fun fetchLotteryResult(@Query("date") date: String): ApiResponse<LotteryResultResponse>
}
