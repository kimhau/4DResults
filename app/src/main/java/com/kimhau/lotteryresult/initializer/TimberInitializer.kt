package com.kimhau.lotteryresult.initializer

import android.content.Context
import androidx.startup.Initializer
import com.kimhau.lotteryresult.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
    Timber.d("TimberInitializer is initialized.")
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
