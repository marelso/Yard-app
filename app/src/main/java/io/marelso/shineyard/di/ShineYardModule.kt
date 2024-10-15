package io.marelso.shineyard.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import io.marelso.shineyard.data.Account
import io.marelso.shineyard.data.Constants
import io.marelso.shineyard.ui.detail.DeviceDetailRepository
import io.marelso.shineyard.data.network.networkModule
import io.marelso.shineyard.data.toEntity
import io.marelso.shineyard.ui.detail.DetailViewModel
import io.marelso.shineyard.ui.list.di.listModule
import io.marelso.shineyard.ui.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val shineYardModule = module {
    factory(named(Constants.KEY_ACCOUNT)) {
        provideAccount(androidContext())
    }

    factory {
        (deviceId: String) -> DeviceDetailRepository(
            database = FirebaseDatabase.getInstance().getReference("/ESP32_Devices/$deviceId")
        )
    }

    factory {
        FirebaseAnalytics.getInstance(androidContext())
    }

    factory(named(Constants.SESSION)) {
        androidContext().getSharedPreferences(
            Constants.KEY_SESSION_PREFERENCE,
            Context.MODE_PRIVATE
        )
    }

    viewModel { (deviceId: String, repository: DeviceDetailRepository) -> DetailViewModel(deviceId = deviceId, repository = repository, analytics = get()) }

    includes(networkModule, loginModule, listModule)
}



fun provideAccount(context: Context): Account {
    val sharedPrefs = context.getSharedPreferences(
        Constants.KEY_SESSION_PREFERENCE,
        Context.MODE_PRIVATE
    )

    return sharedPrefs?.getString(Constants.KEY_ACCOUNT, null)?.toEntity<Account>()  ?: Account()
}
