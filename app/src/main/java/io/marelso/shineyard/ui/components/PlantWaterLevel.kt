package io.marelso.shineyard.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.marelso.shineyard.R
import io.marelso.shineyard.ui.theme.Brand

private val Color.Companion.Water: Color
    get() = Color(0x007DC2D6).copy(alpha = .65F)

@Composable
fun PlantWaterLevelHoisting(moisturePercent: Int) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 180f, animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val waveHeight by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 30f, animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    PlantWaterLevel(
        moisturePercent = moisturePercent, waveOffset = waveOffset, waveHeight = waveHeight
    )
}

@Composable
private fun PlantWaterLevel(
    modifier: Modifier = Modifier, moisturePercent: Int, waveOffset: Float, waveHeight: Float
) {
    Box(
        modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(84.dp)
    ) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 0.70f
                    renderEffect = BlurEffect(
                        radiusX = 7f,
                        radiusY = 7f,
                    )
                },
            painter = painterResource(R.drawable.img_earth_texture),
            contentScale = ContentScale.Crop,
            contentDescription = "Earth image"
        )
        Box(modifier.align(Alignment.BottomStart)) {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(getCurrentWaterLevel(moisturePercent))
                    .background(Color.Water)
            )
            Canvas(modifier = modifier.fillMaxWidth()) {
                drawDynamicWave(waveOffset, waveHeight)
            }
        }

        Column(
            modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        ) {
            Text(
                text = "$moisturePercent%",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
            )

            Text(
                text = stringResource(id = R.string.title_moisture),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
            )
        }

        Button(
            modifier = modifier.padding(end = 16.dp).align(Alignment.CenterEnd),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Brand)
        ) {
            Text(
                text = "Update",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
            )
        }
    }
}

private fun getCurrentWaterLevel(moisturePercent: Int): Dp {
    return (moisturePercent * 84 / 100).dp
}

fun DrawScope.drawDynamicWave(waveOffset: Float, waveHeight: Float) {
    val wavePath = Path()

    val waveLength = 200.dp.toPx()

    wavePath.moveTo(0f, size.height / 2)

    for (x in 0 until size.width.toInt()) {
        val y =
            (size.height / 2) + waveHeight * kotlin.math.sin((x + waveOffset) * (2 * kotlin.math.PI / waveLength))
                .toFloat()
        wavePath.lineTo(x.toFloat(), y)
    }

    wavePath.lineTo(size.width, size.height)
    wavePath.lineTo(0f, size.height)
    wavePath.close()

    drawPath(path = wavePath, color = Color.Water)
}