package com.example.linguaflow.dto

import kotlinx.serialization.Serializable


@Serializable
data class VideoLesson(
    val id: Int,
    val taskText: String,
    val videoPath: String
)
