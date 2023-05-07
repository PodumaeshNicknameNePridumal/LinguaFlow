package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.linguaflow.ui.viewModel.TestViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun TestScreen(
    navigator: DestinationsNavigator,
    testId: Int = -1
) {
    val testViewModel: TestViewModel = getViewModel()
    val testData = testViewModel.testState.collectAsState()
    testViewModel.getQuestions(testId)
    val questions = testData.value.questions
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var numCorrectAnswers by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Test") }
            )
        }
    ) {
        if (questions.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                if (currentQuestionIndex == questions.size) {
                    Text(
                        text = "Final Score: $numCorrectAnswers/${questions.size}",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(onClick = {

                    }
                    , modifier = Modifier.width(200.dp).height(100.dp)) {
                        Text(text = "Закончить текст")
                    }
                }
                else {
                    Text(
                        text = questions[currentQuestionIndex].questionText,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Display answer choices
                    val choices = questions[currentQuestionIndex].choice
                    choices.forEachIndexed { index, choice ->
                        ChoiceItem(
                            choiceText = choice,
                            isSelected = false, // You can add state and handle the selected option here
                            onSelected = { selectedChoice ->
                                // Check if selected choice is the correct answer
                                if (selectedChoice == questions[currentQuestionIndex].answer) {
                                    numCorrectAnswers++
                                }
                                // Move to next question
                                currentQuestionIndex++
                            },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    // Display progress
                    Text(
                        text = "Progress: ${currentQuestionIndex + 1}/${questions.size}",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ChoiceItem(
    choiceText: String,
    isSelected: Boolean,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // You can add your own styling here
    TextButton(
        onClick = { onSelected.invoke(choiceText) },
        modifier = modifier
    ) {
        Text(
            text = choiceText,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
