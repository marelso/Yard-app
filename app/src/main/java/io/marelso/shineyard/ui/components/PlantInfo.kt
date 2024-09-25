package io.marelso.shineyard.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.marelso.shineyard.R
import io.marelso.shineyard.ui.components.text.TextBody
import io.marelso.shineyard.ui.components.text.TextLabel
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantInfo(
    modifier: Modifier = Modifier,
    currentMoisturePercent: Int,
    lastPumpActivateDateTime: String
) {
    Row {
        Column(modifier.weight(1F)) {
            TextLabel(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(id = R.string.title_last_watering_time)
            )
            TextBody(
                modifier = modifier.fillMaxWidth(),
                text = millisToFormattedDate(lastPumpActivateDateTime)
            )
        }

        Column(modifier.weight(1F)) {
            TextLabel(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(id = R.string.title_soil_moisture_level)
            )
            TextBody(
                modifier = modifier.fillMaxWidth(),
                text = "$currentMoisturePercent%"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun millisToFormattedDate(dateTimeString: String): String {
    val a = if(dateTimeString.isNotBlank()) dateTimeString else "2024-09-25T03:02Z"

    val zonedDateTime = ZonedDateTime.parse(a, DateTimeFormatter.ISO_ZONED_DATE_TIME).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM HH:mm")
    return zonedDateTime.format(formatter)
}