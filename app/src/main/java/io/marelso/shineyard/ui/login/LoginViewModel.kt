package io.marelso.shineyard.ui.login

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import io.marelso.shineyard.ui.login.data.network.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val _emailValue = MutableStateFlow(TextFieldValue(""))
    val emailValue: StateFlow<TextFieldValue> = _emailValue

    private val _passwordValue = MutableStateFlow(TextFieldValue(""))
    val passwordValue: StateFlow<TextFieldValue> = _passwordValue

    fun onUserEmailChange(value: TextFieldValue) = _emailValue.tryEmit(value)

    fun onUserPasswordChange(value: TextFieldValue) = _passwordValue.tryEmit(value)
    fun onSubmit() {
        TODO("Not yet implemented")
    }

}