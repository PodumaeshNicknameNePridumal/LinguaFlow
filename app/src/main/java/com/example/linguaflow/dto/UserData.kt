package com.example.linguaflow.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    @SerialName("userID")
    val userId: Int,
    val login: String,
    val password: String,
    val fullName: String,
    val progress: Int,
    val totalPoints: Int,
    val userRole: String,
)
