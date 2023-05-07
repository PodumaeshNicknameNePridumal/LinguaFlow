package com.example.linguaflow.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linguaflow.R
import com.example.linguaflow.ui.screen.destinations.MainScreenDestination
import com.example.linguaflow.ui.screen.destinations.SignUpScreenDestination
import com.example.linguaflow.ui.viewModel.AuthorizationViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun AuthorizationScreen(
    navigator: DestinationsNavigator,
) {
    val authViewModel: AuthorizationViewModel = getViewModel()
    val authData = authViewModel.authState.collectAsState()
    val authError = Toast.makeText(LocalContext.current, "Проверьте корректность данных", Toast.LENGTH_SHORT)
    if (authData.value.auth) {
        navigator.navigate(MainScreenDestination())
    }
    Card(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.logIn),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = authData.value.login,
                onValueChange = { authViewModel.setLogin(it) },
                label = { Text(stringResource(id = R.string.loginLine)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = authData.value.password,
                onValueChange = { authViewModel.setPassword(it) },
                label = { Text(stringResource(id = R.string.passwordLine)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    runBlocking {
                        authViewModel.logIn()
                    }
                    if (authData.value.auth) {
                        navigator.navigate(MainScreenDestination())
                    }
                    else {
                        authError.show()
                    }
                          },
                modifier = Modifier.align(Alignment.CenterHorizontally).width(100.dp).height(45.dp)
            ) {
                Text(stringResource(id = R.string.LogInBtn), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navigator.navigate(SignUpScreenDestination())
                          },
                modifier = Modifier.align(Alignment.CenterHorizontally).width(100.dp).height(45.dp)
            ) {
                Text(stringResource(id = R.string.toSingUpBtn), fontSize = 16.sp)
            }
        }
    }
}
