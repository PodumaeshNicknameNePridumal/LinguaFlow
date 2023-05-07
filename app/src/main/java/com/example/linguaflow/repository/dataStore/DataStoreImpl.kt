package com.example.linguaflow.repository.dataStore

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream


@Serializable
class User(
    var id : Int = -1,
    var name: String = "",
    var progress: Int = 0,
    var maxProgress: Int = -1,
    var version: Int  = -1,
    var role: String = ""
)


class DataStoreImpl(
    context: Context,
) : DataStore {
    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        private val serializer = object : Serializer<User> {
            override val defaultValue: User = User()

            override suspend fun readFrom(input: InputStream): User {
                return Json.decodeFromStream(input)
            }

            override suspend fun writeTo(t: User, output: OutputStream) {
                Json.encodeToStream(t, output)
            }

        }
    }
    private val Context.jsonDS by dataStore("DataStore.json", serializer)
    private val datastore = context.jsonDS

    override suspend fun saveUser(user: User): Unit = withContext(Dispatchers.IO) {
        datastore.updateData {
            user
        }
    }

    override suspend fun getUser(): User {
        return datastore.data.firstOrNull() ?: User()
    }
}