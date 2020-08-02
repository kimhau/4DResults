package com.kimhau.lotteryresult.ui

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
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
  var context: Context? = null

  init {
    Timber.d("init LotteryResultViewModel")

    lotteryResultLiveData = lotteryResultFetchingLiveData.switchMap { query ->
      isLoading.set(true)
      launchOnViewModelScope {
        lotteryResultRepository.fetchLotteryResult(
          date = query.drawDate,
          onSuccess = { isLoading.set(false) },
          onError = { toastLiveData.postValue(it) }
        ).asLiveData()
      }
    }
  }

  fun fetchLotteryResult(date: String, lotteryName: String) {
    lotteryResultFetchingLiveData.value = Query(date, lotteryName)
  }
}

data class Query(
  val drawDate: String,
  val lotteryName: String
)
