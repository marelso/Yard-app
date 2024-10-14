package io.marelso.shineyard.ui.login.data.network

import io.marelso.shineyard.data.Account
import retrofit2.Response

class AuthRepository(private val dataSource: AuthRemoteDataSource) {
    suspend fun auth(email: String, password: String): Response<Account> = dataSource.auth(email, password)
}