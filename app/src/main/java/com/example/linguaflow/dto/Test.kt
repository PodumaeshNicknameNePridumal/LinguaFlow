package com.example.linguaflow.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val testId: Int,
    val testName: String,
    @SerialName("fullName")
    val creatorName: String,
    val level: String
)
