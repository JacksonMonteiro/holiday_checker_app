package com.jacksonmonteiro.holidaychecker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jacksonmonteiro.holidaychecker.data.local.dao.HolidayDAO
import com.jacksonmonteiro.holidaychecker.data.local.entities.HolidayEntity

@Database(entities = [HolidayEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun holidaysDAO(): HolidayDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "holidays_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}