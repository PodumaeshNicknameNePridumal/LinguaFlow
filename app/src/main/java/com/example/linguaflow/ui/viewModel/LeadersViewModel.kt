package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.dto.Leader
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class LeadersState(
    val leaders: List<Leader> = listOf()
)

@KoinViewModel
class LeadersViewModel(
    private val supabaseDataClient: SupabaseDataClient
): ViewModel() {
    private val _leadersState = MutableStateFlow(LeadersState())
    val leadersState = _leadersState.asStateFlow()

    fun getLeaders() {
        viewModelScope.launch {
            _leadersState.update {
                it.copy(leaders = supabaseDataClient.getLeaders())
            }
        }
    }

}