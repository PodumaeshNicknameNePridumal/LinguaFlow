package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.linguaflow.R
import com.example.linguaflow.ui.screen.destinations.MainScreenDestination
import com.example.linguaflow.ui.viewModel.MainLearningState
import com.example.linguaflow.ui.viewModel.MainLearningViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun MainLearningScreen(
    navigator: DestinationsNavigator
) {
    val mainLearningViewModel: MainLearningViewModel = getViewModel()
    val mainLearningState by mainLearningViewModel.mainLearningState.collectAsState()
    mainLearningViewModel.getLesson()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Обучение") }
            )
        },
        content = {
            if (mainLearningState.lessonBase.lessonType.isEmpty()) {
                LoadingScreen()
            }
            else {
                if (mainLearningState.textLesson.id >= 0 || mainLearningState.videoLesson.id >= 0 || mainLearningState.translateLesson.id >= 0) {
                    LazyColumn() {
                        item {
                            when (mainLearningState.lessonBase.lessonType) {
                                "text" -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        TaskBox(task = mainLearningState.textLesson.taskText)
                                        Text(
                                            text = mainLearningState.textLesson.lessonText,
                                            fontSize = 24.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Continue(mainLearningViewModel = mainLearningViewModel, navigator)
                                    }
                                }
                                "video" -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        TaskBox(task = mainLearningState.videoLesson.taskText)
                                        VideoPlayer(uri = mainLearningState.videoLesson.videoPath )
                                        Continue(mainLearningViewModel = mainLearningViewModel,navigator)
                                    }
                                }
                                "translate" -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        TaskBox(task = mainLearningState.translateLesson.taskText)
                                        TranslationScreen(mainLearningViewModel,mainLearningState,)
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    LoadingScreen()
                }

            }
        }
    )
}

@Composable
fun Continue(
    mainLearningViewModel: MainLearningViewModel,
    navigator: DestinationsNavigator
) {
    Button(
        onClick = {
            mainLearningViewModel.isLast()
            if (mainLearningViewModel.mainLearningState.value.last) {
                navigator.navigate(MainScreenDestination())
            }
            mainLearningViewModel.removeLesson()
            mainLearningViewModel.nextLesson()
            mainLearningViewModel.getLesson()},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Продолжить")
    }
}


@Composable
fun VideoPlayer(uri: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build().also {
                val mediaItem = MediaItem.Builder()
                    .setUri(uri)
                    .build()
                it.setMediaItem(mediaItem)
                it.prepare()
            }
    DisposableEffect(
        AndroidView(factory = {
            StyledPlayerView(context).apply {
                player = exoPlayer
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(400.dp))
    ) {
        onDispose { exoPlayer.release() }
    }
}

@Composable
fun TaskBox(
    task: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = task)
    }
}

@Composable
fun TranslationScreen(
    mainLearningViewModel: MainLearningViewModel,
    mainLearningState: MainLearningState
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            // Поле с английским словом
            Text(
                text = mainLearningState.translateLesson.word,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Поле для ввода перевода пользователем
            val translation = remember { mutableStateOf("") }
            val translationComplete = remember { mutableStateOf(false) }
            OutlinedTextField(
                value = translation.value,
                onValueChange = { translation.value = it.uppercase() }, // Автоматический перевод в верхний регистр
                label = { Text(text = "Перевод") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                enabled = !translationComplete.value,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    // Проверка правильности перевода
                    if (translation.value.uppercase() == mainLearningState.translateLesson.answer) {
                        translationComplete.value = true
                        println(translation.value)
                        println("ну совпало и че")
                        // Выводим иконку, если перевод правильный
                        // Можно заменить на другую реакцию на правильный перевод
                        Icon(
                            painter = painterResource(R.drawable.done),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(20.dp)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Кнопка, неактивная, пока пользователь не введет перевод
            Button(
                onClick = {
                    if (translationComplete.value) {
                        println("пиздабол")
                        mainLearningViewModel.removeLesson()
                        mainLearningViewModel.nextLesson()
                        mainLearningViewModel.getLesson()
                    }
                    println("ну нажал и че ")
                    println(translationComplete.value)
                          },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Перевести",
                    color = if (translationComplete.value) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    )
}
