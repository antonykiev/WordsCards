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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.words.cards.android.login.LoginScreen
import com.words.cards.android.splash.SplashScreen
import com.words.cards.android.word.WordScreen
import com.words.cards.android.word_new.NewWordScreen
import com.words.cards.android.wordlist.WordListScreen
import com.words.cards.presentation.event.MainEvent
import com.words.cards.presentation.event.NewWordEvent
import com.words.cards.presentation.intent.MainIntent
import com.words.cards.presentation.state.NewWordScreenContent
import com.words.cards.presentation.state.State
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
            ) { paddingValues ->
                InitNavigation(paddingValues)
            }
        }
    }

    @Composable
    private fun InitNavigation(
        paddingValues: PaddingValues,
    ) {
        val viewModel = koinViewModel<MainViewModel>()
        val state: State<Unit, MainEvent> by viewModel.state.collectAsStateWithLifecycle()

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

        LaunchedEffect(state.event) {
            when (val event = state.event) {
                MainEvent.GoToLogin -> {
                    viewModel.onEventHandled(event)
                    navController.navigate("login")
                }
                MainEvent.GoToWordList -> {
                    viewModel.onEventHandled(event)
                    navController.navigate("word_list")
                }
                MainEvent.Empty -> { }
            }
        }

        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable(route = "splash") {
                SplashScreen(
                    onSuccessfullyLoaded = {
                        println("Main onSuccessfullyLoaded ")
                        viewModel.onIntent(MainIntent.SplashLoaded)
//                        navController.navigate("word_list")
//                        navController.navigate("login")
                    }
                )
            }
            composable(route = "login") {
                LoginScreen(
                    modifier = Modifier
                        .padding(paddingValues),
                    onLoginSuccessfully = {
                        navController.navigate("word_list")
                    }
                )
            }
            composable("word_list") {
                WordListScreen(
                    modifier = Modifier
                        .padding(paddingValues),
                    onOpenNewWord = { newWordText ->
                        navController.navigate("new_word/$newWordText")
                    },
                    onOpenWord = { word ->
                        navController.navigate("word/$word")
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
                        .fillMaxSize()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding(),
                            top = paddingValues.calculateTopPadding(),
                        ),
                    newWord = newWordText,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = "word/{wordId}",
                arguments = listOf(navArgument("wordId") { type = NavType.LongType })
            ) { backStackEntry ->
                val wordId = backStackEntry.arguments?.getLong("wordId")
                    ?: throw IllegalArgumentException("newWordText must be provided")
                WordScreen(
                    modifier = Modifier
                        .padding(paddingValues),
                    wordId = wordId,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
