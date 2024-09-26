package io.marelso.shineyard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

@Composable
fun WaterAmountSelector(
    modifier: Modifier = Modifier,
    value: String,
    onAddAmountClick: () -> Unit,
    onSubtractAmountClick: () -> Unit,
    onSubmitClick: () -> Unit,
    onValueChange: (Int) -> Unit
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    IconButton(onClick = onSubtractAmountClick) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Subtract watering amount",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = { onSubmitClick() }),
        suffix = {
            Text(text = "ml")
        },
        onValueChange = {
            if (it.isNotBlank() && it.isDigitsOnly()) {
                onValueChange(it.toInt())
            } else if (it.isBlank()) {
                onValueChange(0)
            }
        },
        placeholder = {
            Text(text = "Define water amount")
        }
    )

    IconButton(onClick = onAddAmountClick) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Add watering amount",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}