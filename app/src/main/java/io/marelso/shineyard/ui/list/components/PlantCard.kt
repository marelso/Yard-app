package io.marelso.shineyard.ui.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.R
import io.marelso.shineyard.data.Device
import io.marelso.shineyard.ui.theme.Brand
import io.marelso.shineyard.ui.theme.Water

@Composable
fun PlantCard(modifier: Modifier = Modifier, device: Device) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(50.dp))
                .background(color = Color.White)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = modifier
                    .size(50.dp)
                    .padding(4.dp),
                tint = Brand,
                imageVector = ImageVector.vectorResource(R.drawable.ic_plant),
                contentDescription = "Professional years in business",
            )
        }

        Column(modifier.weight(1f)) {
            Text(text = device.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "${device.sensors.currentMoisturePercent}% de umidade",
                style = MaterialTheme.typography.labelLarge,
                color = Water
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = modifier
                    .size(50.dp)
                    .padding(4.dp),
                tint = Brand,
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Professional years in business",
            )
        }
    }
}