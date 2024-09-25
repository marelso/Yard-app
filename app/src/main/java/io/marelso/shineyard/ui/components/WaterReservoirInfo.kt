package io.marelso.shineyard.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.R
import io.marelso.shineyard.ui.components.text.TextBody
import io.marelso.shineyard.ui.components.text.TextLabel

@Composable
fun WaterReservoirInfo(
    modifier: Modifier = Modifier,
    currentWaterVolume: Double,
    maximumWaterVolume: Double
) {
    val a = 20.0
    Column {
        TextLabel(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            text = stringResource(id = R.string.title_water_level)
        )
        Row {
            Box(modifier.weight(1F)) {
                TextBody(
                    modifier = modifier.fillMaxWidth(),
                    text = String.format("%.2f", currentWaterVolume)
                )
            }

            Box(modifier.weight(1F)) {
                TextBody(
                    modifier = modifier.fillMaxWidth(),
                    text = String.format("%.2f", maximumWaterVolume),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}