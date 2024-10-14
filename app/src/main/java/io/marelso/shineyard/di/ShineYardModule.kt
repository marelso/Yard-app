package io.marelso.shineyard.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import io.marelso.shineyard.data.network.FirebaseRepository
import io.marelso.shineyard.data.network.networkModule
import io.marelso.shineyard.ui.detail.DetailViewModel
import io.marelso.shineyard.ui.login.LoginViewModel
import io.marelso.shineyard.ui.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val shineYardModule = module {
    factory {
        FirebaseRepository(database = FirebaseDatabase.getInstance().getReference("sensor"))
    }

    factory {
        FirebaseAnalytics.getInstance(androidContext())
    }

    viewModelOf(::DetailViewModel)

    includes(networkModule, loginModule)
}