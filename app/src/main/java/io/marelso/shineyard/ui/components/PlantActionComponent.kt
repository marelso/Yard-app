package io.marelso.shineyard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.data.PlantAction
import io.marelso.shineyard.ui.theme.Brand

@Composable
fun PlantActionComponent(modifier: Modifier = Modifier, action: PlantAction) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(id = action.title),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Image(
            modifier = modifier.align(Alignment.CenterHorizontally).size(80.dp),
            imageVector = ImageVector.vectorResource(action.icon),
            contentDescription = "Schedule"
        )

        Text(
            text = stringResource(id = action.empty),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )

        OutlinedButton(
            modifier = modifier.align(Alignment.CenterHorizontally),
            content = {
                Text(
                    text = stringResource(id = action.cta),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Brand,
                )
            },
            border = BorderStroke(color = Brand, width = 1.dp),
            onClick = {
                action.onCreate?.invoke()
            }
        )
    }
}