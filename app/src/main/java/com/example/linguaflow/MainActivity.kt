package com.example.linguaflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.linguaflow.ui.screens.NavGraphs
import com.example.linguaflow.ui.theme.SkyNotEngTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkyNotEngTheme() {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

