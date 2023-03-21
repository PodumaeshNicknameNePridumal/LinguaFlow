package com.example.linguaflow.repository.languageRepository

import com.example.linguaflow.dto.CommonData

interface SupabaseDataClient {
    suspend fun getCommonData(): CommonData
    suspend fun addData()
}