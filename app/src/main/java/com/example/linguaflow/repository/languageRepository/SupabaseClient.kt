package com.example.linguaflow.repository.languageRepository

import android.util.Log
import com.example.linguaflow.dto.*
import com.example.linguaflow.repository.dataStore.DataStore
import com.example.linguaflow.repository.dataStore.User
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import org.koin.core.annotation.Single


@Single
class SupabaseDataClientImpl(
    private val dataStore: DataStore
) : SupabaseDataClient {
    private var user = User(
        -1,"",-1,-1
    )
    private val supabaseUrl = "https://pcsrohdpguhrhoyynvcu.supabase.co"
    private val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBjc3JvaGRwZ3VocmhveXludmN1Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY3OTI5NjM5NiwiZXhwIjoxOTk0ODcyMzk2fQ._tBfBtJW9E2mP-QPJOWI5rSqknLBm0TSgyM2co9iRG0"
    private val client = createSupabaseClient(
        supabaseUrl,supabaseKey
    ) {
        install(Postgrest)
    }

    override suspend fun getCommonData() : VersionData {
        val result = client.postgrest["public", "versiondata"].select().decodeList<VersionData>().getOrNull(0)
        println(result?.maxProgress ?: 100)
        user.maxProgress = result?.maxProgress ?: 0
        dataStore.saveUser(user = user)
        println(user.maxProgress)
        return result?: VersionData(-1,0)
    }

    override suspend fun getLesson(): Lesson  {
        println("progress${user.progress}")
        val lesson = client.postgrest["public", "Lessons"].select{
            Lesson::progressNumber eq user.progress
        }.decodeList<Lesson>().firstOrNull() ?: Lesson(-1,"", -1)
        Log.e("lesson", lesson.toString())
        return lesson
    }

    override suspend fun logIn(login: String, password: String): Boolean  {
        println("progress${user.progress}")
        val validUser = client.postgrest["public", "Users"].select{
            UserData::login eq login
            UserData::password eq password
        }.decodeList<UserData>().firstOrNull() ?: UserData(-1,"","","",-1, -1,"")
        Log.e("user", validUser.toString())
        println("dawfawfawfaw")
        if (validUser.userId >= 0) {
            val commonData = getCommonData()
            val userToSave = User(validUser.userId,validUser.fullName,validUser.progress,commonData.maxProgress, commonData.version, validUser.userRole)
            println("hay")
            dataStore.saveUser(userToSave)
            user = userToSave
            return true
        }
        return false
    }

    override suspend fun getSpecLesson(lesson: Lesson): Any? {
        when (lesson.lessonType) {
            "text" -> {
                val textLesson = client.postgrest["public", "TextLessons"].select{
                    TextLesson::id eq lesson.lessonId
                }.decodeList<TextLesson>().firstOrNull() ?: TextLesson(-1,"", "")
                Log.e("Textlesson", textLesson.toString())
                return textLesson
            }
            "translate" -> {
                val translateLesson = client.postgrest["public", "TranslateLessons"].select{
                    TranslateLesson::id eq lesson.lessonId
                }.decodeList<TranslateLesson>().firstOrNull() ?: TranslateLesson(-1,"", "", "")
                Log.e("translesson", translateLesson.toString())
                return translateLesson
            }
            "video" -> {
                val videoLesson = client.postgrest["public", "VideoLessons"].select{
                    VideoLesson::id eq lesson.lessonId
                }.decodeList<VideoLesson>().firstOrNull() ?: VideoLesson(-1,"", "")
                Log.e("videolesson", videoLesson.toString())
                return videoLesson
            }
            else -> {
                return null
            }
        }
    }

    override suspend fun nextLesson() {
        user.progress++
        client.postgrest["Users"]
            .update(
                {
                    UserData::progress setTo user.progress
                }
            ) {
                UserData::userId eq user.id
            }
        dataStore.saveUser(user = user)
    }

    override suspend fun getTests(): List<Test> {
        return client.postgrest["testsinfo"].select().decodeList()
    }

    override suspend fun getQuestions(testId: Int): List<Question> {
        return client.postgrest["Question"].select {
            eq("testId", testId)
        }.decodeList<Question>()
    }
}