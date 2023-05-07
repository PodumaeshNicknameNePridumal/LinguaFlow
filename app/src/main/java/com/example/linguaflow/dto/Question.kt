package com.example.linguaflow.dto

import kotlinx.serialization.Serializable


@Serializable
data class Question(
    val questionId: Int,
    val choice: List<String>,
    val answer: String,
    val questionText: String,
    val testId: Int,
)
