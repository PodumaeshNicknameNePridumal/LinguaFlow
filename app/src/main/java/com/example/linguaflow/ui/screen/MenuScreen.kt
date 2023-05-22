package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaflow.R
import com.example.linguaflow.ui.screen.destinations.MainLearningScreenDestination
import com.example.linguaflow.ui.screen.destinations.TestsScreenDestination
import com.example.linguaflow.ui.theme.BiruzDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.core.annotation.Single

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "RememberReturnType")
@Destination
@Single
@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {
   Scaffold(topBar = {
       Header(navigator)
   }) {
       MainContent(navigator)
   }
}

@Composable
fun Header(navigator: DestinationsNavigator) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Text(
            text =  "LinguaFlow",
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun MainContent(navigator: DestinationsNavigator) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedClickableBox(onClick = { navigator.navigate(TestsScreenDestination()) }, text = "Tests", imageId =R.drawable.tests_icon )
        Spacer(modifier = Modifier.padding(30.dp))
        RoundedClickableBox(onClick = { navigator.navigate(MainLearningScreenDestination())  }, text = "Learning", imageId = R.drawable.edu_icon )
    }
}

@Composable
fun RoundedClickableBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    imageId: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.primary)
            .border(BorderStroke(4.dp, BiruzDark), shape = RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(15.dp)
            .size(180.dp))
    {
        Icon(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(145.dp),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
