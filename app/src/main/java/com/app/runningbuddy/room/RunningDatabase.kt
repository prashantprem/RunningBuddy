package com.app.runningbuddy.room

import androidx.room.Database
import androidx.room.TypeConverters

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase {

    abstract fun getRunDao() : RunDAO
}