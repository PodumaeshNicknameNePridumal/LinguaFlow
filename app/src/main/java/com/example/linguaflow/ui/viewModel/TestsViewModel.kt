package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.dto.Test
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class TestsState(
    val testList: List<Test> = listOf()
)

@KoinViewModel
class TestsViewModel(
    private val supabaseDataClient: SupabaseDataClient
): ViewModel() {
    private val _testsState = MutableStateFlow(TestsState())
    val testsState = _testsState.asStateFlow()

    fun getTests() {
        viewModelScope.launch {
            _testsState.update {
                it.copy(testList = supabaseDataClient.getTests())
            }
        }
    }

    fun getRole(): String {
        return supabaseDataClient.getRole()
    }

}