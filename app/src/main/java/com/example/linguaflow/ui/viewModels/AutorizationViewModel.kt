package com.example.linguaflow.ui.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Single


data class AuthorizationState(
    val login : String = "",
    val password : String = "",
)

@Single
class AutorizationViewModel: ViewModel() {
    private val _authState = MutableStateFlow(AuthorizationState())
    val authState = _authState.asStateFlow()
    fun setLogin(value: String) {
        _authState.update {
            it.copy(login = value)
        }
    }
    fun setPassword(value: String) {
        _authState.update {
            it.copy(password = value)
        }
    }
}