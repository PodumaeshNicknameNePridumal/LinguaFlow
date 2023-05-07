package com.example.linguaflow.dto

import kotlinx.serialization.Serializable


@Serializable
data class TranslateLesson(
    val id: Int,
    val taskText: String,
    val word: String,
    val answer: String
)
