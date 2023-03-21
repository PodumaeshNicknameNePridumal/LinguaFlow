package com.example.linguaflow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.linguaflow.R
import com.example.linguaflow.ui.screens.destinations.TrollfaceDestination
import com.example.linguaflow.ui.viewModels.SingUpViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


@Destination
@Composable
fun Trollface(
    navigator: DestinationsNavigator
) {
    Image(
        painter = painterResource(id = R.drawable.img_2),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Destination
@Composable
fun SignUpScreen(
    navigator: DestinationsNavigator
) {
    val singUpViewModel: SingUpViewModel = getViewModel()
    val stateData = singUpViewModel.singUpState.collectAsState()
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Button(onClick = { navigator.navigate(TrollfaceDestination) }, modifier = Modifier.offset(x = 130.dp, y = 450.dp).size(70.dp).alpha(0f)) {
    }
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = stateData.value.login,
            onValueChange = {  singUpViewModel.setLogin(it) },
            label = { Text(stringResource(id = R.string.loginLine)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = stateData.value.password,
            onValueChange = { singUpViewModel.setPassword(it) },
            label = { Text(stringResource(id = R.string.passwordLine)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = stateData.value.name,
            onValueChange = { singUpViewModel.setName(it) },
            label = { Text(stringResource(id = R.string.fullnameLine)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle sign-up button click */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(id = R.string.SingUpBtn))
        }
    }
}

