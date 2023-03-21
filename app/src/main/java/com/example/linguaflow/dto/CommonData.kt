package com.example.linguaflow.dto

import kotlinx.serialization.Serializable

@Serializable
data class CommonData(
    val version: Int,
    val maxProgress: Int
)
