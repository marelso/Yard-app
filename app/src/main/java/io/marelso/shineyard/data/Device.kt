package io.marelso.shineyard.data

data class Device(
    var id: String? = null,
    val name: String = "",
    val sensors: Sensor = Sensor()
)