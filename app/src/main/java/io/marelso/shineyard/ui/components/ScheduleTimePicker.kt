package io.marelso.shineyard.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.util.Date

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ScheduleTimePicker(
    modifier: Modifier = Modifier,
    state: TimePickerState = rememberTimePickerState(
        initialHour = Date().hours,
        initialMinute = Date().minutes,
        is24Hour = false
    )
) = Box(
    modifier = modifier.fillMaxWidth(),
) {
    TimeInput(
        modifier = modifier.align(Alignment.Center),
        state = state
    )
}