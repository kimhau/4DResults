package com.kimhau.lotteryresult.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kimhau.lotteryresult.model.LotteryResultResponse

@Database(entities = [LotteryResultResponse::class], version = 1, exportSchema = true)
@TypeConverters(value = [ ResultResponseConverter::class, ResultDetailConverter::class, StringListConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao
}
