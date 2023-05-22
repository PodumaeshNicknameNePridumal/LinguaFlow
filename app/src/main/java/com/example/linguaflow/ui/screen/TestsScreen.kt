package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaflow.R
import com.example.linguaflow.dto.Test
import com.example.linguaflow.ui.screen.destinations.CreateTestScreenDestination
import com.example.linguaflow.ui.screen.destinations.LeadersScreenDestination
import com.example.linguaflow.ui.screen.destinations.TestScreenDestination
import com.example.linguaflow.ui.theme.CreateBtn
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
            TestsTopBar(
                testsViewModel,
                title = "Tests",
                onLeadersClick = { navigator.navigate(LeadersScreenDestination())},
                onCreateTestClick = { navigator.navigate(CreateTestScreenDestination())},
                onNavigationClick = { navigator.popBackStack()})
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

@Composable
fun TestsTopBar(
    viewModel: TestsViewModel,
    title: String,
    onLeadersClick: () -> Unit,
    onCreateTestClick: () -> Unit,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 4.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onNavigationClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colors.primaryVariant)
        }
        Text(
            text =  title,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        if (viewModel.getRole() == "creator") {
            Button(
                onClick = onCreateTestClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Create Test",
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "Create test",
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        }
        IconButton(onClick = onLeadersClick) {
            Icon(painter = painterResource(id =R.drawable.leaders_icon), contentDescription = "Leaders", tint = MaterialTheme.colors.primaryVariant)
        }
    }
}
