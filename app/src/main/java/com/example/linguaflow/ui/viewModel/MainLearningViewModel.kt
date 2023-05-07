package com.example.linguaflow.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linguaflow.dto.Lesson
import com.example.linguaflow.dto.TextLesson
import com.example.linguaflow.dto.TranslateLesson
import com.example.linguaflow.dto.VideoLesson
import com.example.linguaflow.repository.languageRepository.SupabaseDataClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


data class MainLearningState(
    val lessonBase: Lesson = Lesson(-1,"",0),
    val textLesson: TextLesson = TextLesson(-1, "", ""),
    val videoLesson: VideoLesson = VideoLesson(-1, "", ""),
    val translateLesson: TranslateLesson = TranslateLesson(-1, "", "", "loading...")
)

@KoinViewModel
class MainLearningViewModel(
    private val supabaseDataClient: SupabaseDataClient,
): ViewModel() {
    private val _mainLearningState = MutableStateFlow(MainLearningState())
    val mainLearningState = _mainLearningState.asStateFlow()

//    fun addData() {
//        viewModelScope.launch {
//            supabaseDataClient.addData()
//        }
//    }

    fun getLesson() {
        viewModelScope.launch {
            _mainLearningState.update {
                it.copy(lessonBase = supabaseDataClient.getLesson())
            }
            _mainLearningState.update {
                when (it.lessonBase.lessonType) {
                    "text" -> {
                        it.copy(textLesson = supabaseDataClient.getSpecLesson(it.lessonBase) as TextLesson)
                    }
                    "translate" -> {
                        it.copy(translateLesson = supabaseDataClient.getSpecLesson(it.lessonBase) as TranslateLesson)
                    }
                    "video" -> {
                        it.copy(videoLesson = supabaseDataClient.getSpecLesson(it.lessonBase) as VideoLesson)
                    }
                    else -> {
                        it.copy(lessonBase = Lesson(-1,"", -1))
                    }
                }
            }
        }
    }

    fun nextLesson() {
        viewModelScope.launch {
            supabaseDataClient.nextLesson()
        }
    }
}