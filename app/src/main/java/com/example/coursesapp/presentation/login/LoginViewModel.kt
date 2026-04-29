package com.example.coursesapp.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val emailRegex = Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")

    fun onEmailChanged(email: String) {
        updateUiState(email, _uiState.value.password)
    }

    fun onPasswordChanged(password: String) {
        updateUiState(_uiState.value.email, password)
    }

    private fun updateUiState(email: String, password: String) {
        _uiState.value = LoginUiState(
            email = email,
            password = password,
            isLoginEnabled = email.matches(emailRegex) && password.isNotBlank()
        )
    }
}