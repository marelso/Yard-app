package io.marelso.shineyard.data

data class Sensor(
    val currentMoistureLevel: Int = 0,
    val currentMoisturePercent: Int = 0,
    val currentWaterVolume: Float = 0.0f,
    val maximumWaterVolume: Float = 0.0f
)
