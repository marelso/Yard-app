package io.marelso.shineyard.ui.login.data.network

import io.marelso.shineyard.data.Account
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("/api/v1/auth")
    suspend fun logIn(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Account>
}