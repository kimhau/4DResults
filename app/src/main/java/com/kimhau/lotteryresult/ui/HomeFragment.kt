package com.kimhau.lotteryresult.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kimhau.lotteryresult.R
import com.kimhau.lotteryresult.base.DataBindingFragment
import com.kimhau.lotteryresult.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : DataBindingFragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, null)
            .apply {
                lifecycleOwner = this@HomeFragment
                navigation = findNavController()
            }.root
    }
}