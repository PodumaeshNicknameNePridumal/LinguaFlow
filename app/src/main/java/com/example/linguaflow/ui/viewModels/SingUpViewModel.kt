package com.example.linguaflow.ui.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Single

data class RegistrationState(
    val login : String = "",
    val password : String = "",
    val name : String = ""
)

@Single
class SingUpViewModel: ViewModel() {
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
}