package com.example.linguaflow.di

import android.app.Application
import com.example.linguaflow.repository.dataStore.DataStore
import com.example.linguaflow.repository.dataStore.DataStoreImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.defaultModule

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(dataStoreModule, defaultModule)
        }
    }
}


val dataStoreModule = module {
    single<DataStore> { DataStoreImpl(get()) }
}



@Module
@ComponentScan
class AppModule

