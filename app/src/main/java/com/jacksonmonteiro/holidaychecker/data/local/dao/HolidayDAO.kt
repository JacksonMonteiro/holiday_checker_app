package com.jacksonmonteiro.holidaychecker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jacksonmonteiro.holidaychecker.data.local.entities.HolidayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HolidayDAO {
    @Query("SELECT * FROM ${HolidayEntity.TABLE_NAME} WHERE year = :year AND contryCode = :countryCode")
    fun getByYearAndContry(year: Int, countryCode: String): Flow<List<HolidayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(): Long
}