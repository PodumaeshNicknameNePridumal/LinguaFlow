package com.example.linguaflow.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.linguaflow.dto.Question
import com.example.linguaflow.dto.Question2
import com.example.linguaflow.ui.screen.destinations.TestsScreenDestination
import com.example.linguaflow.ui.viewModel.CreateTestViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
//
//@Composable
//fun CreateQuestionSection(
//    viewModel: CreateTestViewModel,
//    navigator: DestinationsNavigator
//) {
//    val questionTextState = remember { mutableStateOf("") }
//    val choiceState = remember { mutableStateListOf("") }
//    val answerState = remember { mutableStateOf("") }
//
//    Column {
//        Text("Create Question", style = MaterialTheme.typography.h6)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = questionTextState.value,
//            onValueChange = { questionTextState.value = it },
//            label = { Text("Question Text") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        choiceState.forEachIndexed { index, choice ->
//            Row(Modifier.fillMaxWidth()) {
//                TextField(
//                    value = choice,
//                    onValueChange = { choiceState[index] = it },
//                    label = { Text("Choice ${index + 1}") },
//                    modifier = Modifier.weight(1f)
//                )
//
//                if (index > 0) {
//                    IconButton(
//                        onClick = {
//                            choiceState.removeAt(index)
//                        },
//                        modifier = Modifier.padding(start = 8.dp)
//                    ) {
//                        Icon(Icons.Default.Delete, contentDescription = "Remove Choice")
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = answerState.value,
//            onValueChange = { answerState.value = it },
//            label = { Text("Answer") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.End
//        ) {
//            IconButton(
//                onClick = {
//                    choiceState.add("")
//                },
//                modifier = Modifier.padding(end = 8.dp)
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add Choice")
//            }
//
//            Button(
//                onClick = {
//                    val question = Question(
//                        questionId = 0,
//                        choice = choiceState.filter { it.isNotEmpty() },
//                        answer = answerState.value,
//                        questionText = questionTextState.value,
//                        testId = viewModel.createTestState.value.testId
//                    )
//                    viewModel.addQuestion(question)
//
//                    // Reset input fields
//                    questionTextState.value = ""
//                    choiceState.clear()
//                    choiceState.add("")
//                    answerState.value = ""
//                }
//            ) {
//                Text("Add Question")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))

//        Button(
//            onClick = {
//                // Reset input fields
//                questionTextState.value = ""
//                choiceState.clear()
//                choiceState.add("")
//                answerState.value = ""
//                navigator.navigate(TestsScreenDestination())
//            },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Text("Finish Test Creation")
//        }
//    }
//}

@Composable
fun CreateQuestionSection(
    viewModel: CreateTestViewModel,
    navigator: DestinationsNavigator
) {
    val questionTextState = remember { mutableStateOf("") }
    val choiceState = remember { mutableStateListOf("") }
    val answerState = remember { mutableStateOf("") }

    Column {
        Text("Create Question", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = questionTextState.value,
            onValueChange = { questionTextState.value = it },
            label = { Text("Question Text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        choiceState.forEachIndexed { index, choice ->
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = choice,
                    onValueChange = { choiceState[index] = it },
                    label = { Text("Choice ${index + 1}") },
                    modifier = Modifier.weight(1f)
                )

                if (index == choiceState.size - 1) {
                    IconButton(
                        onClick = {
                            choiceState.add("")
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Choice")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = answerState.value,
            onValueChange = { answerState.value = it },
            label = { Text("Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    val question = Question2(
                        choice = choiceState.filter { it.isNotEmpty() },
                        answer = answerState.value,
                        questionText = questionTextState.value,
                        testId = viewModel.createTestState.value.testId
                    )
                    println(question)
                    viewModel.addQuestion(question)
                    // Reset input fields
                    questionTextState.value = ""
                    choiceState.clear()
                    choiceState.add("")
                    answerState.value = ""
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Add Question")
            }

            Button(
                onClick = {
                    val question = Question2(
                        choice = choiceState.filter { it.isNotEmpty() },
                        answer = answerState.value,
                        questionText = questionTextState.value,
                        testId = viewModel.createTestState.value.testId
                    )
                    println(question)
                    viewModel.addQuestion(question)

                    // Reset input fields
                    questionTextState.value = ""
                    choiceState.clear()
                    choiceState.add("")
                    answerState.value = ""
                    navigator.navigate(TestsScreenDestination())
                    ///
                }
            ) {
                Text("Finish Test Creation")
            }
        }
    }
}


@Destination
@Composable
fun CreateTestScreen(
    navigator: DestinationsNavigator
    ) {
    val viewModel: CreateTestViewModel = getViewModel()
    val viewModelData = viewModel.createTestState.collectAsState()


    val testNameState = remember { mutableStateOf("") }
    val levelState = remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        if (viewModelData.value.testId == -1) {
            item {
                TextField(
                    value = testNameState.value,
                    onValueChange = { testNameState.value = it },
                    label = { Text("Test Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = levelState.value,
                    onValueChange = { levelState.value = it },
                    label = { Text("Level") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val testName = testNameState.value
                        val level = levelState.value
                        if (testName.isNotEmpty() && level.isNotEmpty()) {
                            viewModel.createTest(testName, level)
                        }
                    },
                ) {
                    Text("Continue")
                }
            }
        } else if (viewModelData.value.testId > -1) {
            item {
                CreateQuestionSection(viewModel, navigator)
            }
        }
    }
}
