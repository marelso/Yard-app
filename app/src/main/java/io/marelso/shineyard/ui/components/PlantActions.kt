package io.marelso.shineyard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.R
import io.marelso.shineyard.ui.components.text.TextLabel

@Composable
fun PlantActions(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextLabel(text = stringResource(id = R.string.title_action))
        Row {
            Action(action = stringResource(id = R.string.title_action_schedule_watering))
        }
    }
}

@Composable
private fun Action(
    modifier: Modifier = Modifier,
    action: String
) {
    Column(
        modifier = modifier.clickable { },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = modifier
                .size(70.dp)
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(1.dp)
                )
                .clip(RoundedCornerShape(1.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = modifier.size(64.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plant),
                contentDescription = "app icon",
                tint = Color(0xFFA4E465)
            )
        }
        TextLabel(
            modifier = modifier.width(70.dp),
            text = action,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}