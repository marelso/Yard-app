package io.marelso.shineyard.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import io.marelso.shineyard.data.Account
import io.marelso.shineyard.data.Constants
import io.marelso.shineyard.data.Session
import io.marelso.shineyard.data.network.FirebaseRepository
import io.marelso.shineyard.data.network.networkModule
import io.marelso.shineyard.data.toEntity
import io.marelso.shineyard.ui.detail.DetailViewModel
import io.marelso.shineyard.ui.list.di.listModule
import io.marelso.shineyard.ui.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val shineYardModule = module {
    factory(named(Constants.KEY_ACCOUNT)) {
        provideAccount(androidContext())
    }

    factory {
        FirebaseRepository(database = FirebaseDatabase.getInstance().getReference("sensor"))
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

    viewModelOf(::DetailViewModel)

    includes(networkModule, loginModule, listModule)
}



fun provideAccount(context: Context): Account {
    val sharedPrefs = context.getSharedPreferences(
        Constants.KEY_SESSION_PREFERENCE,
        Context.MODE_PRIVATE
    )

    return sharedPrefs?.getString(Constants.KEY_ACCOUNT, null)?.toEntity<Account>()  ?: Account()
}
