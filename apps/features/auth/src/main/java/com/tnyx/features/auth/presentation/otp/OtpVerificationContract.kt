package com.tnyx.features.auth.presentation.otp

data class OtpVerificationUiState(
    val email: String,
    val code: String = "",
    val codeError: String? = null,
    val statusMessage: String? = null,
    val isLoading: Boolean = false
) {
    val canSubmit: Boolean
        get() = code.length == 6 && !isLoading
}

sealed interface OtpVerificationAction {
    data class CodeChanged(val value: String) : OtpVerificationAction
    data object VerifyClicked : OtpVerificationAction
    data object ResendClicked : OtpVerificationAction
    data object BackClicked : OtpVerificationAction
}

sealed interface OtpVerificationEffect {
    data object Authenticated : OtpVerificationEffect
    data object NavigateBack : OtpVerificationEffect
}
