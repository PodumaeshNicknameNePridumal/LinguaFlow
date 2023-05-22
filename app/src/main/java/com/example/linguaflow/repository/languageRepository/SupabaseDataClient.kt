package com.example.linguaflow.repository.languageRepository

import com.example.linguaflow.dto.*

interface SupabaseDataClient {
    suspend fun getCommonData(): VersionData
    suspend fun getLesson(): Lesson
    suspend fun getSpecLesson(lesson: Lesson): Any?
    suspend fun logIn(login: String, password: String): Boolean
    suspend fun nextLesson(): Boolean
    suspend fun getTests(): List<Test>
    suspend fun getQuestions(testId: Int): List<Question>
    suspend fun endTest(result: Int, testId: Int)
    suspend fun getLeaders(): List<Leader>
    suspend fun createTest(testName: String, testLevel: String): Int
    suspend fun addQuestion(question: Question2)
    suspend fun singUp(name: String, password: String, login: String)
    suspend fun isLast(): Boolean
    fun getRole(): String
}