package com.kimhau.lotteryresult.network

import javax.inject.Inject

class LotteryResultClient @Inject constructor(
  private val lotteryResultService: LotteryResultService
) {

  suspend fun fetcLotteryResult(
    date: String
  ) = lotteryResultService.fetchLotteryResult(
    date = date
  )
}
