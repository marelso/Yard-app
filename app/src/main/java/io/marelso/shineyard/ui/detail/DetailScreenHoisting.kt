package io.marelso.shineyard.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.marelso.shineyard.R
import io.marelso.shineyard.data.PlantAction
import io.marelso.shineyard.ui.components.PlantActions
import io.marelso.shineyard.ui.components.PlantInfo
import io.marelso.shineyard.ui.components.PlantPicture
import io.marelso.shineyard.ui.components.WaterReservoirInfo
import io.marelso.shineyard.ui.components.text.TextHeadline
import io.marelso.shineyard.ui.components.text.TextLabel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreenHoisting(
    viewModel: DetailViewModel,
    redirectToSchedule: () -> Unit
) {
    val moistureLevel by viewModel.currentMoisturePercent.collectAsStateWithLifecycle()
    val lastPumpActivateDateTime by viewModel.lastPumpActivateDateTime.collectAsStateWithLifecycle()
    val maximumWaterVolume by viewModel.maximumWaterVolume.collectAsStateWithLifecycle()
    val currentWaterVolume by viewModel.currentWaterVolume.collectAsStateWithLifecycle()
    val pumpActiveStatus by viewModel.pumpActiveStatus.collectAsStateWithLifecycle()

    DetailScreen(
        lastPumpActivateDateTime = lastPumpActivateDateTime,
        currentMoisturePercent = moistureLevel,
        maximumWaterVolume = maximumWaterVolume,
        currentWaterVolume = currentWaterVolume,
        pumpActiveStatus = pumpActiveStatus,
        onPumpStatusChange = viewModel::onPumpStatusChange,
        onScheduleClick = redirectToSchedule
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailScreen(
    modifier: Modifier = Modifier,
    currentMoisturePercent: Int,
    maximumWaterVolume: Double,
    currentWaterVolume: Double,
    pumpActiveStatus: Boolean,
    lastPumpActivateDateTime: String,
    onScheduleClick: () -> Unit,
    onPumpStatusChange: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { })
        },
        content = {
            LazyColumn(
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = it
            ) {
                item {
                    WaterReservoirInfo(
                        maximumWaterVolume = maximumWaterVolume,
                        currentWaterVolume = currentWaterVolume
                    )
                }
                item {
                    PlantPicture()
                }
                item {
                    TextHeadline(text = "Schweppes")
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
                                onClick = onScheduleClick
                            }
                        )
                    )
                }
            }
        },
        bottomBar = {
            Row(
                modifier = modifier
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