package io.marelso.shineyard.ui.login.data.network

import io.marelso.shineyard.data.Account
import retrofit2.Response

class AuthRemoteDataSource(private val service: AuthService) {
    suspend fun auth(email: String, password: String): Response<Account> {
        return service.logIn(email = email, password = password)
    }
}