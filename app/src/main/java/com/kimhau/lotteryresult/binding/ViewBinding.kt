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

package com.kimhau.lotteryresult.binding

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.kimhau.lotteryresult.model.LotteryResultResponse
import com.kimhau.lotteryresult.ui.HomeFragmentDirections
import com.kimhau.lotteryresult.ui.LotteryResultViewModel
import com.skydoves.whatif.whatIfNotNull
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
  text.value.whatIfNotNull {
    Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
  }
}

@BindingAdapter("onClick")
fun bindOnClick(view: View, navController: NavController) {
  if (view is Button) {
    view.setOnClickListener {
      navController.navigate(HomeFragmentDirections.actionHomeFragmentToResultFragment(view.text.toString()))
    }
  }
}

@BindingAdapter("onClick", "drawDate", "lotteryName")
fun bindOnClick(view: View, viewModel: LotteryResultViewModel, drawDate: String, lotteryName: String) {
  val sdf = SimpleDateFormat("yyyy-M-d")
  val todayDate = sdf.format(Date())
  if (todayDate == drawDate) {
    view.setOnClickListener {
      viewModel.fetchLotteryResult(drawDate, lotteryName, true)
    }
  } else {
    view.visibility = View.GONE
  }
}

@BindingAdapter("date", "viewModel", "lotteryName")
fun bindDate(view: View, date: String, viewModel: LotteryResultViewModel, lotteryName: String) {
  if (view is EditText) {
    view.setText(date)
    view.setOnClickListener {
      val cldr: Calendar = Calendar.getInstance()
      val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
      val month: Int = cldr.get(Calendar.MONTH)
      val year: Int = cldr.get(Calendar.YEAR)
      val picker = DatePickerDialog(
        view.context,
        OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
          val newDate = year.toString() + "-" + (monthOfYear + 1).toString() + "-" + dayOfMonth.toString()
          view.setText(newDate)
          viewModel.fetchLotteryResult(newDate, lotteryName)
        },
        year,
        month,
        day
      ).apply {
        datePicker.maxDate = Date().time
      }
      picker.show()
    }
  }
}

@BindingAdapter("results", "viewModel", "lotteryName")
fun bindResults(view: View, results: LiveData<LotteryResultResponse?>, viewModel: LotteryResultViewModel, lotteryName: String) {
  results.value.whatIfNotNull {
    when (lotteryName) {
      "Magnum" -> viewModel.lotteryResultByNameLiveData.postValue(it.magnum.apply {
        if (this == null) viewModel.toastLiveData.postValue("No draw on selected date.")
      })
      "Damacai" -> viewModel.lotteryResultByNameLiveData.postValue(it.damacai.apply {
        if (this == null) viewModel.toastLiveData.postValue("No draw on selected date.")
      })
      "Toto" -> viewModel.lotteryResultByNameLiveData.postValue(it.toto.apply {
        if (this == null) viewModel.toastLiveData.postValue("No draw on selected date.")
      })
      else -> viewModel.lotteryResultByNameLiveData.postValue(it.singaporePool.apply {
        if (this == null) viewModel.toastLiveData.postValue("No draw on selected date.")
      })
    }
  }
}

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
  view.visibility = if (shouldBeGone) {
    View.GONE
  } else {
    View.VISIBLE
  }
}
