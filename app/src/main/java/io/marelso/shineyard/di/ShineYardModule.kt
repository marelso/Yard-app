package io.marelso.shineyard.di

import com.google.firebase.database.FirebaseDatabase
import io.marelso.shineyard.data.network.FirebaseRepository
import io.marelso.shineyard.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val shineYardModule = module {
    factory {
        FirebaseRepository(database = FirebaseDatabase.getInstance().getReference("sensor"))
    }

    viewModelOf(::DetailViewModel)
}