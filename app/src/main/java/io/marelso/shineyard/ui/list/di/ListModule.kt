package io.marelso.shineyard.ui.list.di

import io.marelso.shineyard.data.Account
import io.marelso.shineyard.data.Constants
import io.marelso.shineyard.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val listModule = module {
    viewModel {
        ListViewModel(get<Account>(named(Constants.KEY_ACCOUNT)))
    }
}