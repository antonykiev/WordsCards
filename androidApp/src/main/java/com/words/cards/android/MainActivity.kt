package com.words.cards.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.words.cards.android.login.LoginScreen
import com.words.cards.android.main.MainScreen
import com.words.cards.android.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InitNavigation()
                }
            }
        }
    }

    @Composable
    private fun InitNavigation() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route.orEmpty()
        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable(route = "splash") {
                SplashScreen(
                    onSuccessfullyLoaded = {
                        navController.navigate("login")
                    }
                )
            }
            composable(route = "login") {
                LoginScreen(
                    onLoginSuccessfully = {
                        navController.navigate("main")
                    }
                )
            }
            composable(route = "main") {
                MainScreen()
            }
        }
    }
}
