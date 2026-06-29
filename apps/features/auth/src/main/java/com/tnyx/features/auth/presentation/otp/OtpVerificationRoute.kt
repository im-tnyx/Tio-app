package com.tnyx.features.auth.presentation.otp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OtpVerificationRoute(
    email: String,
    onAuthSuccess: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: OtpVerificationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                OtpVerificationEffect.Authenticated -> onAuthSuccess()
                OtpVerificationEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    OtpVerificationScreen(
        state = uiState.copy(email = uiState.email.ifBlank { email }),
        onAction = viewModel::handleAction
    )
}
