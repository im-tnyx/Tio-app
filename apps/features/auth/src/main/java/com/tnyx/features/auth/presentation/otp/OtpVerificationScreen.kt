package com.tnyx.features.auth.presentation.otp

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
import androidx.compose.ui.unit.dp
import com.tnyx.core.theme.TnyxTheme
import com.tnyx.core.ui.components.buttons.TnyxGhostButton
import com.tnyx.core.ui.components.buttons.TnyxPrimaryButton
import com.tnyx.core.ui.components.inputs.TnyxTextField

@Composable
fun OtpVerificationScreen(
    state: OtpVerificationUiState,
    onAction: (OtpVerificationAction) -> Unit,
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
                text = "Verify email",
                style = TnyxTheme.typography.displaySmall,
                color = TnyxTheme.colors.textPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter the code sent to ${state.email.ifBlank { "your email" }}.",
                style = TnyxTheme.typography.bodyLarge,
                color = TnyxTheme.colors.textSecondary
            )

            Spacer(modifier = Modifier.height(32.dp))

            TnyxTextField(
                value = state.code,
                onValueChange = { onAction(OtpVerificationAction.CodeChanged(it)) },
                label = { Text("6-digit code") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                isError = state.codeError != null,
                errorMessage = state.codeError,
                helperMessage = state.statusMessage
            )

            Spacer(modifier = Modifier.height(24.dp))

            TnyxPrimaryButton(
                text = if (state.isLoading) "Verifying" else "Verify",
                onPressed = { onAction(OtpVerificationAction.VerifyClicked) },
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

            Spacer(modifier = Modifier.height(12.dp))

            TnyxGhostButton(
                text = "Resend code",
                onPressed = { onAction(OtpVerificationAction.ResendClicked) },
                enabled = !state.isLoading,
                expand = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TnyxGhostButton(
                text = "Back",
                onPressed = { onAction(OtpVerificationAction.BackClicked) },
                enabled = !state.isLoading
            )
        }
    }
}
