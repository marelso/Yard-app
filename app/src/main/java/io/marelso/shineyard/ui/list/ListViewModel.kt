package io.marelso.shineyard.ui.list

import androidx.lifecycle.ViewModel
import io.marelso.shineyard.data.Account

class ListViewModel(private val currentAccount: Account): ViewModel() {
    val user = currentAccount.email
}