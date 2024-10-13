package io.marelso.shineyard.ui.login.di

import io.marelso.shineyard.data.Constants
import io.marelso.shineyard.ui.login.LoginViewModel
import io.marelso.shineyard.ui.login.data.network.AuthRemoteDataSource
import io.marelso.shineyard.ui.login.data.network.AuthRepository
import io.marelso.shineyard.ui.login.data.network.AuthService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val loginModule = module {
    factory {
        get<Retrofit>(named(Constants.YARD_API.NAME)).create(AuthService::class.java)
    }
    factoryOf(::AuthRemoteDataSource)
    factoryOf(::AuthRepository)

    viewModelOf(::LoginViewModel)
}