package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.dto.Question
import com.example.linguaflow.dto.Question2
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel



data class CreateTestState(
    val testId : Int = -1
)

@KoinViewModel
class CreateTestViewModel(
    private val supabaseDataClient: SupabaseDataClient,
    ): ViewModel() {
    private val _createTestState = MutableStateFlow(CreateTestState())
    val createTestState = _createTestState.asStateFlow()


    fun createTest(name: String, level: String) {
        viewModelScope.launch {
            _createTestState.update {
                it.copy(testId = supabaseDataClient.createTest(name, level))
            }
        }
    }

    fun addQuestion(question: Question2) {
        viewModelScope.launch {
            supabaseDataClient.addQuestion(question)
        }
    }
}