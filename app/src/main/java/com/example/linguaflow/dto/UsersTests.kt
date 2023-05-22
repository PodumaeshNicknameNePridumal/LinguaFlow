package com.example.linguaflow.dto

import kotlinx.serialization.Serializable


@Serializable
data class UsersTests(
    val testId: Int,
    val userId: Int,
    val result: Int
)
