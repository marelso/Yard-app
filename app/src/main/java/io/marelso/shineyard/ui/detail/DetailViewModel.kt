package io.marelso.shineyard.ui.detail

import android.os.Bundle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import io.marelso.shineyard.data.WaterSchedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

class DetailViewModel(
    private val deviceId: String,
    private val analytics: FirebaseAnalytics,
    private val repository: DeviceDetailRepository
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

    private val _waterAmount = MutableStateFlow(0)
    val waterAmount: StateFlow<Int> = _waterAmount

    @OptIn(ExperimentalMaterial3Api::class)
    val scheduleTimeState = TimePickerState(
        initialHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        initialMinute = Calendar.getInstance().get(Calendar.MINUTE),
        is24Hour = false
    )

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
        analytics.logEvent("pump_status_change", Bundle().apply {
            putBoolean("status", !pumpActiveStatus.value)
        })

        repository.changePumpStatus(!pumpActiveStatus.value)
    }

    fun onWaterAmountChange(value: Int) {
        _waterAmount.tryEmit(value)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun onSubmitClick() {
        analytics.logEvent("watering_schedule", Bundle().apply {
            putInt("amount", _waterAmount.value)
            putString("time", "${scheduleTimeState.hour}:${scheduleTimeState.minute}")
        })
        repository.registerSchedule(
            WaterSchedule(
                date = "${scheduleTimeState.hour}:${scheduleTimeState.minute}",
                amount = _waterAmount.value
            )
        )
    }
}