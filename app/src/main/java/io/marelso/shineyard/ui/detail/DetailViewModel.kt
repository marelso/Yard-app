package io.marelso.shineyard.ui.detail

import androidx.lifecycle.ViewModel
import io.marelso.shineyard.data.network.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
    private val repository: FirebaseRepository
): ViewModel() {
    private val _currentMoisturePercent = MutableStateFlow(0)
    val currentMoisturePercent: StateFlow<Int> = _currentMoisturePercent

    private val _lastPumpActivateDateTime = MutableStateFlow("")
    val lastPumpActivateDateTime: StateFlow<String> = _lastPumpActivateDateTime

    private val _maximumWaterVolume = MutableStateFlow(0.0)
    val maximumWaterVolume: StateFlow<Double> = _maximumWaterVolume

    private val _currentWaterVolume = MutableStateFlow(0.0)
    val currentWaterVolume: StateFlow<Double> = _currentWaterVolume

    private val _pumpActiveStatus = MutableStateFlow(false)
    val pumpActiveStatus: StateFlow<Boolean> = _pumpActiveStatus

    init {
        repository.currentMoisturePercent {
            _currentMoisturePercent.tryEmit(it)
        }

        repository.maximumWaterVolume {
            _maximumWaterVolume.tryEmit(it)
        }

        repository.currentWaterVolume {
            _currentWaterVolume.tryEmit(it)
        }

        repository.pumpActiveStatus {
            _pumpActiveStatus.tryEmit(it)
        }

        repository.lastPumpActivateDateTime {
            _lastPumpActivateDateTime.tryEmit(it)
        }
    }

    fun onPumpStatusChange() {
        repository.changePumpStatus(!pumpActiveStatus.value)
    }
}