package com.example.carecircle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class CareCircleDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: CareCircleDatabase? = null

        fun getDatabase(context: Context): CareCircleDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CareCircleDatabase::class.java,
                    "carecircle_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}