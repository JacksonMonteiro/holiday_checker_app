package com.jacksonmonteiro.holidaychecker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jacksonmonteiro.holidaychecker.data.local.entities.HolidayEntity.Companion.TABLE_NAME

@Entity(TABLE_NAME)
data class HolidayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var year: Int,
    var contryCode: String,
    var holidays: String,
) {
    companion object {
        const val TABLE_NAME = "holidays"
    }
}
