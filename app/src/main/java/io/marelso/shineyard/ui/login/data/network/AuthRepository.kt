package io.marelso.shineyard.ui.login.data.network

class AuthRepository(private val dataSource: AuthRemoteDataSource) {
    suspend fun auth(email: String, password: String) = dataSource.auth(email, password)
}