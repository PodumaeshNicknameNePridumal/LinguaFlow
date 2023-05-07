package com.example.linguaflow.dto

import kotlinx.serialization.Serializable

@Serializable
data class TextLesson(
    val id: Int,
    val lessonText: String,
    val taskText: String
)
