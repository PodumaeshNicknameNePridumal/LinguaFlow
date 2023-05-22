package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel


data class MenuState(
    val name : String = ""
)

@KoinViewModel
class MenuViewModel(
    private val supabaseDataClient: SupabaseDataClient
): ViewModel() {
    private val _menuState = MutableStateFlow(MenuState())
    val menuState = _menuState.asStateFlow()

    fun getName() {
        _menuState.update {
            it.copy(name = supabaseDataClient.getName())
        }
    }
}
