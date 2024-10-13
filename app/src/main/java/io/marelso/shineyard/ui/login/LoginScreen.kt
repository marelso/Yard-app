package io.marelso.shineyard.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.marelso.shineyard.R

@Composable
fun LoginScreenHoisting(viewModel: LoginViewModel) {
    val email by viewModel.emailValue.collectAsStateWithLifecycle()
    val password by viewModel.passwordValue.collectAsStateWithLifecycle()
    var showPassword by remember { mutableStateOf(false) }

    LoginScreen(
        email = email,
        password = password,
        showPassword = showPassword,
        onChangePasswordVisibility = { showPassword = !showPassword },
        onUserEmailChange = viewModel::onUserEmailChange,
        onUserPasswordChange = viewModel::onUserPasswordChange,
        onSubmit = viewModel::onSubmit
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    email: TextFieldValue,
    password: TextFieldValue,
    showPassword: Boolean,
    onChangePasswordVisibility: () -> Unit,
    onUserEmailChange: (TextFieldValue) -> Unit,
    onUserPasswordChange: (TextFieldValue) -> Unit,
    onSubmit: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = modifier.padding(bottom = 16.dp),
                value = email,
                placeholder = {
                    Text(
                        text = "Email",
                        fontSize = 16.sp,
                    )
                },
                onValueChange = onUserEmailChange
            )

            TextField(
                modifier = modifier.padding(bottom = 16.dp),
                value = password,
                placeholder = {
                    Text(
                        text = "Password",
                        fontSize = 16.sp,
                    )
                },
                visualTransformation = getPasswordVisual(showPassword),
                trailingIcon = {
                    val (icon, iconColor) = if (showPassword) {
                        Pair(
                            ImageVector.vectorResource(R.drawable.ic_visibility),
                            MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Pair(
                            ImageVector.vectorResource(R.drawable.ic_visibility_off),
                            MaterialTheme.colorScheme.secondary
                        )
                    }

                    IconButton(onClick = onChangePasswordVisibility) {
                        Icon(
                            icon,
                            contentDescription = "Visibility",
                            tint = iconColor
                        )
                    }
                },
                onValueChange = onUserPasswordChange
            )

            Button(
                modifier = modifier
                    .width(TextFieldDefaults.MinWidth)
                    .padding(bottom = 16.dp),
                enabled = getSubmitStatus(password.text, email.text),
                onClick = onSubmit) {
                Text(text = "Log in")
            }

            TextButton(modifier = modifier.padding(bottom = 16.dp), onClick = { /*TODO*/ }) {
                Text(text = "Forgot password")
            }
        }
    }
}

private fun getSubmitStatus(password: String, email: String) = password.isNotBlank() && email.isNotBlank()

private fun getPasswordVisual(showPassword: Boolean) =
    if (showPassword) VisualTransformation.None else PasswordVisualTransformation()