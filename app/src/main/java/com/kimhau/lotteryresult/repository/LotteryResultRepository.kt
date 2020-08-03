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

package com.kimhau.lotteryresult.repository

import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.kimhau.lotteryresult.network.LotteryResultClient
import com.kimhau.lotteryresult.persistence.ResultDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LotteryResultRepository @Inject constructor(
  private val lotteryResultClient: LotteryResultClient,
  private val resultDao: ResultDao
) {

  suspend fun fetchLotteryResult(
    date: String,
    isForceUpdate: Boolean = false,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow<LotteryResultResponse?> {
    val lotteryResult = resultDao.getResult(date)
    if (lotteryResult == null || isForceUpdate) {
      val response = lotteryResultClient.fetcLotteryResult(date = date)
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          response.drawDate = date
          resultDao.insertResult(response)
          emit(response)
          onSuccess()
        }
      }
        // handle the case when the API request gets an error response.
        // e.g. internal server error.
        .onError {
          onError(message())
        }
        // handle the case when the API request gets an exception response.
        // e.g. network connection error.
        .onException {
          onError(message())
        }
    } else {
      emit(lotteryResult)
      onSuccess()
    }
  }.flowOn(Dispatchers.IO)
}
