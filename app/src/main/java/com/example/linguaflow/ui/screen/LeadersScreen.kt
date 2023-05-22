package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linguaflow.dto.Leader
import com.example.linguaflow.ui.viewModel.LeadersViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun LeadersScreen(
    navigator: DestinationsNavigator,
) {
    val leadersViewModel: LeadersViewModel = getViewModel()
    val leadersData = leadersViewModel.leadersState.collectAsState()
    leadersViewModel.getLeaders()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tests") }
            )
        }
    ) {
        if (leadersData.value.leaders.isEmpty()) {
            LoadingScreen()
        }
        else {
            LazyColumn {
                items(leadersData.value.leaders) {
                    LeaderboardItemRow( leader = it)
                }
            }
        }
    }
}

// Функция для отображения элементов таблицы лидеров
@Composable
fun LeaderboardItemRow(leader: Leader) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = leader.fullName,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        Text(text = leader.totalPoints.toString())
    }
}
