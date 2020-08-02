package com.kimhau.lotteryresult.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kimhau.lotteryresult.R
import com.kimhau.lotteryresult.base.DataBindingFragment
import com.kimhau.lotteryresult.databinding.FragmentHomeBinding
import com.kimhau.lotteryresult.databinding.FragmentResultBinding
import com.kimhau.lotteryresult.ui.adapter.ResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class ResultFragment : DataBindingFragment() {
    private val args by navArgs<ResultFragmentArgs>()
    @VisibleForTesting
    val viewModel by viewModels<LotteryResultViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        return binding<FragmentResultBinding>(inflater, R.layout.fragment_result, null)
            .apply {
                lifecycleOwner = this@ResultFragment
                vm = viewModel.apply {
                    fetchLotteryResult(currentDate)
                }
                lotteryName = args.lotteryName
                specialPrizesAdapter = ResultAdapter()
                consolationPrizesAdapter = ResultAdapter()
                date = currentDate
            }.root
    }
}