package io.marelso.shineyard.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.marelso.shineyard.R
import io.marelso.shineyard.data.PlantAction
import io.marelso.shineyard.ui.components.PlantActionComponent
import io.marelso.shineyard.ui.components.PlantActions
import io.marelso.shineyard.ui.components.PlantInfo
import io.marelso.shineyard.ui.components.PlantTopBar
import io.marelso.shineyard.ui.components.PlantWaterLevelHoisting
import io.marelso.shineyard.ui.components.ScheduleBottomSheet
import io.marelso.shineyard.ui.components.text.TextLabel
import io.marelso.shineyard.ui.theme.Brand

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreenHoisting(
    viewModel: DetailViewModel,
    navigateBack: () -> Unit,
    redirectToSchedule: () -> Unit
) {
    val moistureLevel by viewModel.currentMoisturePercent.collectAsStateWithLifecycle()
    val lastPumpActivateDateTime by viewModel.lastPumpActivateDateTime.collectAsStateWithLifecycle()
    val maximumWaterVolume by viewModel.maximumWaterVolume.collectAsStateWithLifecycle()
    val currentWaterVolume by viewModel.currentWaterVolume.collectAsStateWithLifecycle()
    val pumpActiveStatus by viewModel.pumpActiveStatus.collectAsStateWithLifecycle()
    val waterAmount by viewModel.waterAmount.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var shouldShowScheduleSheet by remember {
        mutableStateOf(false)
    }

    DetailScreen(
        shouldShowScheduleSheet = shouldShowScheduleSheet,
        lastPumpActivateDateTime = lastPumpActivateDateTime,
        currentMoisturePercent = moistureLevel,
        maximumWaterVolume = maximumWaterVolume,
        currentWaterVolume = currentWaterVolume,
        pumpActiveStatus = pumpActiveStatus,
        waterAmount = waterAmount.toString(),
        sheetState = sheetState,
        scheduleTimeState = viewModel.scheduleTimeState,
        onAddAmountClick = {
            viewModel.onWaterAmountChange(waterAmount + 10)
        },
        onSubtractAmountClick = {
            viewModel.onWaterAmountChange(waterAmount - 10)
        },
        onWaterAmountChange = viewModel::onWaterAmountChange,
        onPumpStatusChange = viewModel::onPumpStatusChange,
        onScheduleSheetVisibilityChange = {
            shouldShowScheduleSheet = it
        },
        onSubmitClick = viewModel::onSubmitClick,
        navigateBack = navigateBack
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailScreen(
    modifier: Modifier = Modifier,
    shouldShowScheduleSheet: Boolean,
    currentMoisturePercent: Int,
    maximumWaterVolume: Double,
    currentWaterVolume: Double,
    pumpActiveStatus: Boolean,
    lastPumpActivateDateTime: String,
    waterAmount: String,
    sheetState: SheetState,
    scheduleTimeState: TimePickerState,
    onAddAmountClick: () -> Unit,
    onSubtractAmountClick: () -> Unit,
    onWaterAmountChange: (Int) -> Unit,
    onScheduleSheetVisibilityChange: (Boolean) -> Unit,
    onPumpStatusChange: () -> Unit,
    navigateBack: () -> Unit,
    onSubmitClick: () -> Unit
) {
    Scaffold(
        topBar = {
            PlantTopBar(navigateBack = navigateBack)
        },
        content = {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = it
            ) {

                item {
                    Box(
                        modifier = modifier.padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .offset(y = -(22.dp))
                                .height(100.dp)
                                .clip(
                                    RoundedCornerShape(
                                        bottomStartPercent = 100,
                                        bottomEndPercent = 100
                                    )
                                )
                                .background(Brand)
                        )

                        Box(
                            modifier = modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = 24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2B4D7B))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFA4E465),
                                    shape = CircleShape
                                )
                                .wrapContentSize()
                                .padding(16.dp),
                            content = {
                                Icon(
                                    modifier = modifier.size(112.dp),
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_plant),
                                    contentDescription = "app icon",
                                    tint = Color(0xFFA4E465)
                                )
                            }
                        )
                    }
                }

                item {
                    Column(modifier.padding(horizontal = 16.dp)) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = "Schweppes",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = "A do vaso vermelho",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                item {
                    PlantWaterLevelHoisting(
                        currentMoisturePercent
                    )
                }

                item {
                    PlantActionComponent(action = PlantAction.SCHEDULE)
                }

                item {
                    PlantActionComponent(action = PlantAction.NOTIFICATION)
                }

                item {
                    PlantInfo(
                        currentMoisturePercent = currentMoisturePercent,
                        lastPumpActivateDateTime = lastPumpActivateDateTime
                    )
                }
                item {
                    PlantActions(
                        actions = listOf(
                            PlantAction.SCHEDULE.apply {
                                onCreate = {
                                    onScheduleSheetVisibilityChange(!shouldShowScheduleSheet)
                                }
                            }
                        )
                    )
                }

                if (shouldShowScheduleSheet) {
                    item {
                        ScheduleBottomSheet(
                            value = waterAmount,
                            scheduleTimeState = scheduleTimeState,
                            sheetState = sheetState,
                            onAddAmountClick = onAddAmountClick,
                            onSubtractAmountClick = onSubtractAmountClick,
                            onValueChange = onWaterAmountChange,
                            onSubmitClick = onSubmitClick,
                            onDismissRequest = { onScheduleSheetVisibilityChange(false) }
                        )
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .border(1.dp, color = Color.LightGray)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = onPumpStatusChange,
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(2.dp)
                ) {
                    TextLabel(
                        text = if (pumpActiveStatus) {
                            stringResource(R.string.title_action_trigger_pump_off)
                        } else {
                            stringResource(R.string.title_action_trigger_pump)
                        }
                    )
                }
            }
        }
    )
}