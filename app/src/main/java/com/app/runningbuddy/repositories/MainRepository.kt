package com.app.runningbuddy.repositories

import com.app.runningbuddy.room.Run
import com.app.runningbuddy.room.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortedByDate()
    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()
    fun getAllRunsSortedByTimeInMillis() = runDAO.getAllRunsSortedByTimeInMillis()
    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()
    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()
    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()
    fun getTotalDistance() = runDAO.getTotalDistance()
    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()

}