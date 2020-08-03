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

package com.kimhau.lotteryresult.ui

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.kimhau.lotteryresult.base.LiveCoroutinesViewModel
import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.kimhau.lotteryresult.model.ResultDetail
import com.kimhau.lotteryresult.repository.LotteryResultRepository
import timber.log.Timber

class LotteryResultViewModel @ViewModelInject constructor(
  private val lotteryResultRepository: LotteryResultRepository
) : LiveCoroutinesViewModel() {

  private var lotteryResultFetchingLiveData: MutableLiveData<Query> = MutableLiveData()
  val lotteryResultLiveData: LiveData<LotteryResultResponse?>
  var lotteryResultByNameLiveData: MutableLiveData<ResultDetail?> = MutableLiveData()
  val isLoading: ObservableBoolean = ObservableBoolean(false)
  val toastLiveData: MutableLiveData<String> = MutableLiveData()

  init {
    Timber.d("init LotteryResultViewModel")

    lotteryResultLiveData = lotteryResultFetchingLiveData.switchMap { query ->
      isLoading.set(true)
      launchOnViewModelScope {
        lotteryResultRepository.fetchLotteryResult(
          date = query.drawDate,
          isForceUpdate = query.isForceUpdate,
          onSuccess = { isLoading.set(false) },
          onError = { toastLiveData.postValue(it) }
        ).asLiveData()
      }
    }
  }

  fun fetchLotteryResult(date: String, lotteryName: String, isForceUpdate: Boolean = false) {
    lotteryResultFetchingLiveData.value = Query(date, lotteryName, isForceUpdate)
  }
}

data class Query(
  val drawDate: String,
  val lotteryName: String,
  val isForceUpdate: Boolean
)
