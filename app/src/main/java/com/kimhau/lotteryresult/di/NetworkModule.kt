package com.kimhau.lotteryresult.di

import com.kimhau.lotteryresult.network.HttpRequestInterceptor
import com.kimhau.lotteryresult.network.LotteryResultClient
import com.kimhau.lotteryresult.network.LotteryResultService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://4dyes.com/")
      .addConverterFactory(MoshiConverterFactory.create())
//      .addConverterFactory(GsonConverterFactor.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideLotteryResultService(retrofit: Retrofit): LotteryResultService {
    return retrofit.create(LotteryResultService::class.java)
  }

  @Provides
  @Singleton
  fun provideLotteryResultClient(lotteryResultService: LotteryResultService): LotteryResultClient {
    return LotteryResultClient(lotteryResultService)
  }
}
