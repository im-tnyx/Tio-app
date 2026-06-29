package com.tnyx.features.auth.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.tnyx.core.theme.TnyxTheme
import com.tnyx.core.ui.components.buttons.TnyxGhostButton
import com.tnyx.core.ui.components.buttons.TnyxPrimaryButton
import com.tnyx.core.ui.components.inputs.TnyxTextField

@Composable
fun SignupScreen(
    state: SignupUiState,
    onAction: (SignupAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TnyxTheme.colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Create account",
                style = TnyxTheme.typography.displaySmall,
                color = TnyxTheme.colors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Set up your profile and verify your email.",
                style = TnyxTheme.typography.bodyLarge,
                color = TnyxTheme.colors.textSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            TnyxTextField(
                value = state.name,
                onValueChange = { onAction(SignupAction.NameChanged(it)) },
                label = { Text("Name") },
                isError = state.nameError != null,
                errorMessage = state.nameError
            )

            Spacer(modifier = Modifier.height(14.dp))

            TnyxTextField(
                value = state.email,
                onValueChange = { onAction(SignupAction.EmailChanged(it)) },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = state.emailError != null,
                errorMessage = state.emailError
            )

            Spacer(modifier = Modifier.height(14.dp))

            TnyxTextField(
                value = state.password,
                onValueChange = { onAction(SignupAction.PasswordChanged(it)) },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = state.passwordError != null,
                errorMessage = state.passwordError
            )

            Spacer(modifier = Modifier.height(24.dp))

            TnyxPrimaryButton(
                text = if (state.isLoading) "Creating account" else "Create account",
                onPressed = { onAction(SignupAction.SignupClicked) },
                enabled = state.canSubmit,
                expand = true,
                leading = if (state.isLoading) {
                    {
                        CircularProgressIndicator(
                            modifier = Modifier.height(18.dp),
                            strokeWidth = 2.dp,
                            color = TnyxTheme.colors.background
                        )
                    }
                } else {
                    null
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                style = TnyxTheme.typography.bodyMedium,
                color = TnyxTheme.colors.textSecondary
            )
            TnyxGhostButton(
                text = "Sign in",
                onPressed = { onAction(SignupAction.LoginClicked) },
                enabled = !state.isLoading
            )
        }
    }
}
