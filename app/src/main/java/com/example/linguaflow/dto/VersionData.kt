package com.example.linguaflow.dto

import kotlinx.serialization.Serializable

@Serializable
data class VersionData(
    val version: Int,
    val maxProgress: Int
)
