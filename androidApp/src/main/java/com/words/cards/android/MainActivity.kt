package com.words.cards.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.words.cards.android.login.LoginScreen
import com.words.cards.android.main.MainScreen
import com.words.cards.android.splash.SplashScreen
import com.words.cards.android.word.NewWordScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                ) { paddingValues ->
                    InitNavigation(paddingValues)
                }
            }
        }
    }

    @Composable
    private fun InitNavigation(
        paddingValues: PaddingValues
    ) {
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
                    modifier = Modifier
                        .padding(paddingValues),
                    onLoginSuccessfully = {
                        navController.navigate("main")
                    }
                )
            }
            composable(route = "main") {
                MainScreen(
                    modifier = Modifier
                        .padding(paddingValues),
                    onOpenNewWord = { newWordText ->
                        navController.navigate("new_word/$newWordText")
                    }
                )
            }
            composable(
                route = "new_word/{newWordText}",
                arguments = listOf(navArgument("newWordText") { type = NavType.StringType })
            ) { backStackEntry ->
                val newWordText = backStackEntry.arguments?.getString("newWordText")
                    ?: throw IllegalArgumentException("newWordText must be provided")
                NewWordScreen(
                    modifier = Modifier
                        .padding(paddingValues),
                    newWord = newWordText,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
