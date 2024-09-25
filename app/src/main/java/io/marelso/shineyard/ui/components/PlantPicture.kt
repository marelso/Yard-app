package io.marelso.shineyard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.R

@Composable
fun PlantPicture(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Divider()

        Box(
            modifier = modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFF1C3D5B))
                .border(
                    width = 1.dp,
                    color = Color(0xFFA4E465),
                    shape = RoundedCornerShape(30.dp)
                )
                .wrapContentSize()
                .padding(8.dp),
            content = {
                Icon(
                    modifier = modifier.size(80.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_plant),
                    contentDescription = "app icon",
                    tint = Color(0xFFA4E465)
                )
            }
        )
    }
}