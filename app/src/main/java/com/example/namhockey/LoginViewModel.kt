package com.example.namhockey

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        _uiState.value = LoginUiState(isLoading = true)

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.value = LoginUiState(success = true)
                } else {
                    _uiState.value = LoginUiState(
                        error = task.exception?.message ?: "Login failed"
                    )
                }
            }
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val  error: String? = null
)