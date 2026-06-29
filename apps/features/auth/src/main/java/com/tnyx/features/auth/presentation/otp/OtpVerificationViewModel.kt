package com.tnyx.features.auth.presentation.otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class OtpVerificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val initialEmail = savedStateHandle["email"] ?: ""
    private val _uiState = MutableStateFlow(OtpVerificationUiState(email = initialEmail))
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<OtpVerificationEffect>()
    val effect = _effect.asSharedFlow()

    fun handleAction(action: OtpVerificationAction) {
        when (action) {
            is OtpVerificationAction.CodeChanged -> _uiState.update {
                it.copy(
                    code = action.value.filter(Char::isDigit).take(6),
                    codeError = null,
                    statusMessage = null
                )
            }
            OtpVerificationAction.VerifyClicked -> verify()
            OtpVerificationAction.ResendClicked -> _uiState.update {
                it.copy(statusMessage = "Verification code sent again", codeError = null)
            }
            OtpVerificationAction.BackClicked -> emitEffect(OtpVerificationEffect.NavigateBack)
        }
    }

    private fun verify() {
        val state = _uiState.value
        if (state.code.length != 6) {
            _uiState.update {
                it.copy(codeError = "Enter the 6-digit code")
            }
            return
        }

        _uiState.update { it.copy(isLoading = true) }
        emitEffect(OtpVerificationEffect.Authenticated)
        _uiState.update { it.copy(isLoading = false) }
    }

    private fun emitEffect(effect: OtpVerificationEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
