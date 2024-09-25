package io.marelso.shineyard.ui.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextBody(
    modifier: Modifier = Modifier,
    text: String,
    textOverflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = textOverflow,
        textAlign = textAlign,
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Normal,
        )
    )
}