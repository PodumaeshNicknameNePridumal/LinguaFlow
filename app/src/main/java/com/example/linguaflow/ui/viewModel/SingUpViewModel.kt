package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class RegistrationState(
    val login : String = "",
    val password : String = "",
    val name : String = ""
)

@KoinViewModel
class SingUpViewModel(
    private val supabaseDataClient: SupabaseDataClient
): ViewModel() {
    private val _singUpState = MutableStateFlow(RegistrationState())
    val singUpState = _singUpState.asStateFlow()

    fun setLogin(value: String) {
        _singUpState.update {
            it.copy(login = value)
        }
    }
    fun setPassword(value: String) {
        _singUpState.update {
            it.copy(password = value)
        }
    }
    fun setName(value: String) {
        _singUpState.update {
            it.copy(name = value)
        }
    }

    fun singUp() {
        viewModelScope.launch {
            supabaseDataClient.singUp(_singUpState.value.name,_singUpState.value.password,_singUpState.value.login)
        }
    }
}