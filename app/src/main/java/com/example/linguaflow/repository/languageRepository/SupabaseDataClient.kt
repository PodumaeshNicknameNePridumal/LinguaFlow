package com.example.linguaflow.repository.languageRepository

import com.example.linguaflow.dto.Lesson
import com.example.linguaflow.dto.Question
import com.example.linguaflow.dto.Test
import com.example.linguaflow.dto.VersionData

interface SupabaseDataClient {
    suspend fun getCommonData(): VersionData
    suspend fun getLesson(): Lesson
    suspend fun getSpecLesson(lesson: Lesson): Any?
    suspend fun logIn(login: String, password: String): Boolean
    suspend fun nextLesson()
    suspend fun getTests(): List<Test>
    suspend fun getQuestions(testId: Int): List<Question>
}