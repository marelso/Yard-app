package io.marelso.shineyard.data

data class Sensor(
    val currentMoistureLevel: Int,
    val currentMoisturePercent: Int,
    val currentWaterVolume: Float,
    val maximumWaterVolume: Float
)
