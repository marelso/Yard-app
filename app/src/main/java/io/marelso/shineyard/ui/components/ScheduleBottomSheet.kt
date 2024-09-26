package io.marelso.shineyard.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.ui.components.text.TextHeadline
import io.marelso.shineyard.ui.components.text.TextLabel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ScheduleBottomSheet(
    modifier: Modifier = Modifier,
    value: String,
    scheduleTimeState: TimePickerState,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
    onAddAmountClick: () -> Unit,
    onSubtractAmountClick: () -> Unit,
    onValueChange: (Int) -> Unit,
    onSubmitClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier.fillMaxSize(),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        TextHeadline(
            modifier = modifier
                .padding(bottom = 32.dp)
                .align(Alignment.CenterHorizontally),
            text = "Schedule watering"
        )
        ScheduleTimePicker(state = scheduleTimeState)
        WaterAmountSelector(
            value = value,
            onAddAmountClick = onAddAmountClick,
            onSubtractAmountClick = onSubtractAmountClick,
            onSubmitClick = onSubmitClick,
            onValueChange = onValueChange,
        )
        Button(
            modifier = modifier.fillMaxWidth().padding(32.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = onSubmitClick
        ) {
            TextLabel(text = "Submit")
        }
    }
}