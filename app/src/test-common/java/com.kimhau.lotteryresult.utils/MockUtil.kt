package com.kimhau.lotteryresult.utils

import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.kimhau.lotteryresult.model.ResultDetail

object MockUtil {

  fun mockResult() = LotteryResultResponse(
    drawDate = "2020-8-5",
    magnum = mockResultDetail(),
    damacai = mockResultDetail(),
    toto = mockResultDetail(),
    singaporePool = mockResultDetail()
  )



  fun mockResultDetail() = ResultDetail(
    name = "Magnum",
    firstPrize = listOf("1111"),
    secondPrize = listOf("2222"),
    thirdPrize = listOf("3333"),
    specialPrizes = listOf("4444","5555","6666"),
    consolationPrizes = listOf("7777","8888","9999"),
    drawDate = "2020-8-5"
  )
}
