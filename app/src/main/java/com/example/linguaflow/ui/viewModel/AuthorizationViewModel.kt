package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


data class AuthorizationState(
    val login : String = "",
    val password : String = "",
    val auth: Boolean = false
)

@KoinViewModel
class AuthorizationViewModel(
    private val supabaseDataClient: SupabaseDataClient,

    ): ViewModel() {
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

    fun logIn() {
        viewModelScope.launch {
            _authState.update {
                it.copy(auth = supabaseDataClient.logIn(it.login, it.password))
            }
        }
    }
}