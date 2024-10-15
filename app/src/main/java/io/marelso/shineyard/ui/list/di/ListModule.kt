package io.marelso.shineyard.ui.list.di

import com.google.firebase.database.FirebaseDatabase
import io.marelso.shineyard.data.Account
import io.marelso.shineyard.data.Constants
import io.marelso.shineyard.ui.list.DeviceListRepository
import io.marelso.shineyard.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val listModule = module {
    factory {
        DeviceListRepository(database = FirebaseDatabase.getInstance().getReference("/ESP32_Devices"))
    }
    viewModel {
        ListViewModel(
            currentAccount = get<Account>(named(Constants.KEY_ACCOUNT)),
            repository = get()
        )
    }
}