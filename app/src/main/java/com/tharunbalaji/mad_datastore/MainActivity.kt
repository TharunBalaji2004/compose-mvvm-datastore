package com.tharunbalaji.mad_datastore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tharunbalaji.mad_datastore.ui.theme.MaddatastoreTheme
import com.tharunbalaji.mad_datastore.utils.Constants
import com.tharunbalaji.mad_datastore.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaddatastoreTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()

    when (isLoggedIn) {
        Constants.AuthState.LOADING -> LoadingScreen()
        Constants.AuthState.LOGGED_IN -> HomeScreen()
        Constants.AuthState.NOT_LOGGED_IN -> LoginScreen (
            onLoginClick = { loginViewModel.saveAppEntry() }
        )
    }

    Log.d("LOGIN", "isLoggedIn: ${loginViewModel.isLoggedIn.value}")
}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "WELCOME TO LOGIN SCREEN!")
        Button(
            onClick = onLoginClick
        ) {
            Text(text = "LOGIN")
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "WELCOME TO HOME SCREEN!")
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}