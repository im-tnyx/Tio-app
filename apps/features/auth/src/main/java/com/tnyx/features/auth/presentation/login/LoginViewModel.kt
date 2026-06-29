package com.tnyx.features.auth.presentation.login

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
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    fun handleAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged -> _uiState.update {
                it.copy(email = action.value, emailError = null)
            }
            is LoginAction.PasswordChanged -> _uiState.update {
                it.copy(password = action.value, passwordError = null)
            }
            LoginAction.SignInClicked -> submit()
            LoginAction.DemoAccountClicked -> emitEffect(LoginEffect.Authenticated)
            LoginAction.CreateAccountClicked -> emitEffect(LoginEffect.NavigateToSignup)
        }
    }

    private fun submit() {
        val state = _uiState.value
        val email = state.email.trim()
        val password = state.password
        val emailError = when {
            email.isBlank() -> "Email is required"
            "@" !in email -> "Enter a valid email"
            else -> null
        }
        val passwordError = when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }

        if (emailError != null || passwordError != null) {
            _uiState.update {
                it.copy(
                    email = email,
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        _uiState.update { it.copy(email = email, isLoading = true) }
        emitEffect(LoginEffect.Authenticated)
        _uiState.update { it.copy(isLoading = false) }
    }

    private fun emitEffect(effect: LoginEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
