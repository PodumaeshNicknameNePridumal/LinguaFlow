package com.example.linguaflow.repository.languageRepository

import com.example.linguaflow.dto.CommonData
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.jan.supabase.annotiations.SupabaseExperimental
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.plugins.standaloneSupabaseModule
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.PostgrestResult
import org.koin.core.annotation.Single


@Single
class SupabaseDataClientImpl : SupabaseDataClient {
    private val supabaseUrl = "https://pcsrohdpguhrhoyynvcu.supabase.co"
    private val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBjc3JvaGRwZ3VocmhveXludmN1Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY3OTI5NjM5NiwiZXhwIjoxOTk0ODcyMzk2fQ._tBfBtJW9E2mP-QPJOWI5rSqknLBm0TSgyM2co9iRG0"
    val client = createSupabaseClient(
        supabaseUrl,supabaseKey
    ) {
        install(Postgrest)
    }
//
    private val mypostgr = standaloneSupabaseModule(Postgrest, url = supabaseUrl, apiKey = supabaseKey)


    @OptIn(SupabaseExperimental::class)
    override suspend fun getCommonData() : CommonData {
        println("1")
        val result = client.postgrest["public", "versiondata"].select().decodeList<CommonData>().getOrNull(0)
        println("2")
        println(result?.maxProgress ?: 100)
        return result?: CommonData(100,100)
    }
    override suspend fun addData()  {
        client.postgrest["public", "versiondata"].insert(CommonData(10,1000))
    }
}