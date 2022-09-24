package com.app.runningbuddy.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.app.runningbuddy.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    var totalDistance = mainRepository.getTotalDistance()
    var totalTimeInMillis = mainRepository.getTotalTimeInMillis()
    var totalAvgSpeed = mainRepository.getTotalAvgSpeed()
    var totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()

    var runsSortedByDate = mainRepository.getAllRunsSortedByDate()
}