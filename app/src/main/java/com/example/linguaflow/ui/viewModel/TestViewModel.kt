package com.example.linguaflow.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.dto.Question
import com.example.linguaflow.dto.Test
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


data class TestState(
    val questions: List<Question> = listOf()
)

@KoinViewModel
class TestViewModel(
    private val supabaseDataClient: SupabaseDataClient
): ViewModel() {
    private val _testState = MutableStateFlow(TestState())
    val testState = _testState.asStateFlow()

    fun getQuestions(testId: Int) {
        viewModelScope.launch {
            _testState.update {
                it.copy(questions = supabaseDataClient.getQuestions(testId))
            }
        }
    }

    fun endTest(result: Int, testId: Int) {
        viewModelScope.launch {

        }
    }

}