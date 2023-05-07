package com.example.linguaflow.repository.dataStore



interface DataStore {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User
}
