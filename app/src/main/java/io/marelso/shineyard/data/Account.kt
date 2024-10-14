package io.marelso.shineyard.data

data class Account(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val pictureUrl: String? = null,
    val email: String? = null,
    val jwt: String? = null,
    val accountDevices: List<Device> = listOf()
)
