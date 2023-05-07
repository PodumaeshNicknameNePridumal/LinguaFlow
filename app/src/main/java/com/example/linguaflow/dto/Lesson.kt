package com.example.linguaflow.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    @SerialName("progressnumber")
    val progressNumber: Int,
    @SerialName("lessontype")
    val lessonType: String,
    @SerialName("lessonId")
    val lessonId: Int
)
