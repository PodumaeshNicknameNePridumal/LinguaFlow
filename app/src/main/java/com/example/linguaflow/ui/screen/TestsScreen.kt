package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.linguaflow.dto.Test
import com.example.linguaflow.ui.screen.destinations.TestScreenDestination
import com.example.linguaflow.ui.viewModel.TestsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun TestsScreen(
    navigator: DestinationsNavigator,
) {
    val testsViewModel: TestsViewModel = getViewModel()
    val testsData = testsViewModel.testsState.collectAsState()
    testsViewModel.getTests()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tests") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(testsData.value.testList, key = {it.testId}) { test ->
                    TestItem(test, navigator)
                }
            }
        }
    }
}

@Composable
fun TestItem(
    test: Test,
    navigator: DestinationsNavigator
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = {
                    navigator.navigate(TestScreenDestination(testId = test.testId))
                })
                .padding(16.dp)
        ) {
            Text(
                text = test.testName,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = test.creatorName,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = test.level,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
