package com.app.runningbuddy.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.runningbuddy.db.Converters
import com.app.runningbuddy.db.Run
import com.app.runningbuddy.db.RunDao

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao
}