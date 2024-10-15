package io.marelso.shineyard.data

data class Device(
    val id: String? = null,
    val name: String = "",
    val sensors: Sensor = Sensor()
)