/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
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

package com.kimhau.lotteryresult.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimhau.lotteryresult.R
import com.kimhau.lotteryresult.databinding.ItemResultBinding
import com.skydoves.whatif.whatIfNotNullOrEmpty

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

  private val items: MutableList<String> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      DataBindingUtil.inflate<ItemResultBinding>(inflater, R.layout.item_result, parent, false)
    return ResultViewHolder(binding)
  }

  fun addResults(results: List<String>?) {
    items.clear()
    results.whatIfNotNullOrEmpty {
      items.addAll(it)
    }
    notifyDataSetChanged()

  }

  override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
    val item = items[position]
    holder.binding.apply {
      result = item
      executePendingBindings()
    }
  }

  override fun getItemCount() = items.size

  class ResultViewHolder(val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root)
}
