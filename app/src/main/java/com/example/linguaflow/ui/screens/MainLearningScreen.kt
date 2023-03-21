package com.example.linguaflow.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.linguaflow.ui.viewModels.MainLearningState
import com.example.linguaflow.ui.viewModels.MainLearningViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Destination
@Composable
fun MainLearningScreen(
    navigator: DestinationsNavigator
) {
    val mainLearningViewModel: MainLearningViewModel = getViewModel()
    mainLearningViewModel.getCommonData()
    val mainLearningState by mainLearningViewModel.mainLearningState.collectAsState()
    Column() {
        Text(text = mainLearningState.maxProgress.toString())
        Button(onClick = { mainLearningViewModel.getCommonData()}) {
            Text(text = "load")
        }
        Button(onClick = { mainLearningViewModel.addData()}) {
            Text(text = "load")
        }
    }
}

