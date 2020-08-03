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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kimhau.lotteryresult.R
import com.kimhau.lotteryresult.base.DataBindingFragment
import com.kimhau.lotteryresult.databinding.FragmentResultBinding
import com.kimhau.lotteryresult.ui.adapter.ResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class ResultFragment : DataBindingFragment() {
    private val args by navArgs<ResultFragmentArgs>()
    @VisibleForTesting
    val viewModel by viewModels<LotteryResultViewModel>()
    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val sdf = SimpleDateFormat("yyyy-M-d")
        val currentDate = sdf.format(Date())
        return binding<FragmentResultBinding>(inflater, R.layout.fragment_result, null)
            .apply {
                lifecycleOwner = this@ResultFragment
                vm = viewModel.apply {
                    fetchLotteryResult(currentDate, args.lotteryName)
                }
                lotteryName = args.lotteryName
                specialPrizesAdapter = ResultAdapter()
                consolationPrizesAdapter = ResultAdapter()
                date = currentDate
            }.root
    }
}
