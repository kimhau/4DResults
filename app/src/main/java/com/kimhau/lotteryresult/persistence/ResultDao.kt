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

package com.kimhau.lotteryresult.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimhau.lotteryresult.model.LotteryResultResponse

@Dao
interface ResultDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertResult(result: LotteryResultResponse)

  @Query("SELECT * FROM LotteryResultResponse WHERE drawDate = :drawDate_")
  suspend fun getResult(drawDate_: String): LotteryResultResponse
}
