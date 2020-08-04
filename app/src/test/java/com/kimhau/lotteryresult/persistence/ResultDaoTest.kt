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

package com.kimhau.lotteryresult.persistence

import com.kimhau.lotteryresult.utils.MockUtil.mockResult
import com.kimhau.lotteryresult.utils.MockUtil.mockResultDetail
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class ResultDaoTest : LocalDatabase() {

  private lateinit var resultDao: ResultDao

  @Before
  fun init() {
    resultDao = db.resultDao()
  }

  @Test
  fun insertAndLoadResultTest() = runBlocking {
    val mockDataList = mockResult()
    resultDao.insertResult(mockDataList)

    val loadFromDB = resultDao.getResult("2020-8-5")
    assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

    val mockData = mockResultDetail()
    assertThat(loadFromDB.magnum.toString(), `is`(mockData.toString()))
  }
}
