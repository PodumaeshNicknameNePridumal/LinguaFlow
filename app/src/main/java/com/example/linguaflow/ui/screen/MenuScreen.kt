package com.example.linguaflow.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.linguaflow.R
import com.example.linguaflow.ui.screen.destinations.MainLearningScreenDestination
import com.example.linguaflow.ui.screen.destinations.TestsScreenDestination
import com.example.linguaflow.ui.theme.Biruz
import com.example.linguaflow.ui.theme.BiruzDark
import com.ramcosta.composedestinations.annotation.Destination
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
       val context = LocalContext.current
       val configuration = LocalConfiguration.current
       val orientation = remember(configuration) {
           when (configuration.orientation) {
               Configuration.ORIENTATION_LANDSCAPE -> "landscape"
               Configuration.ORIENTATION_PORTRAIT -> "portreit"
               else -> "null"
           }
       }
       MainContent(orientation = orientation, navigator)
   }
}

@Composable
fun Header(navigator: DestinationsNavigator) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(onClick = {navigator.popBackStack()
            }) {
                Icon(Icons.Filled.Person, contentDescription = "Back")
            }
        },
    )
}

@Composable
fun MainContent(orientation : String, navigator: DestinationsNavigator) {
    if (orientation == "landscape") {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedClickableBox(onClick = { navigator.navigate(TestsScreenDestination()) }, text = "Menu item1", imageId = R.drawable.img )
            Spacer(modifier = Modifier.padding(10.dp))
            RoundedClickableBox(onClick = { navigator.navigate(MainLearningScreenDestination())  }, text = "Menu item1", imageId = R.drawable.img )
            Spacer(modifier = Modifier.padding(10.dp))
            RoundedClickableBox(onClick = { /*TODO*/ }, text = "Menu item1", imageId = R.drawable.img )
        }
    } else
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundedClickableBox(onClick = { /*TODO*/ }, text = "Menu item1", imageId = R.drawable.img )
            Spacer(modifier = Modifier.padding(10.dp))
            RoundedClickableBox(onClick = { /*TODO*/ }, text = "Menu item1", imageId = R.drawable.img )
            Spacer(modifier = Modifier.padding(10.dp))
            RoundedClickableBox(onClick = { /*TODO*/ }, text = "Menu item1", imageId = R.drawable.img )
        }
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
            .background(Biruz)
            .border(BorderStroke(4.dp, BiruzDark), shape = RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(15.dp)
            .size(180.dp))
    {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
