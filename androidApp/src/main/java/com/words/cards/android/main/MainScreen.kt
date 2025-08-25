package com.words.cards.android.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.words.cards.android.MyApplicationTheme
import com.words.cards.android.R
import com.words.cards.android.settings.SettingsScreen
import com.words.cards.android.word.NewWordScreen
import com.words.cards.android.wordlist.WordListScreen
import com.words.cards.presentation.event.MainEvent
import com.words.cards.presentation.intent.MainIntent
import com.words.cards.presentation.state.MainScreenContent
import com.words.cards.presentation.state.State
import com.words.cards.resource.Image
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onOpenNewWord: (newWord: String) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val viewModel = koinViewModel<MainViewModel>()
    val state: State<MainScreenContent, MainEvent> by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(MainIntent.InitialLoad)
    }

    Log.d("MainScreen", "state: ${state}")

    LaunchedEffect(state.event) {
        when (val event = state.event) {
            MainEvent.Empty -> {

            }

            is MainEvent.OpenWord -> {
                Log.d("MainScreen", "onOpenNewWord: ${event.newWord}")
                viewModel.onEventHandled(event)
                onOpenNewWord(event.newWord)
            }
        }
    }

    MainPane(
        modifier = modifier,
        navController = navController,
        currentRoute = currentRoute,
        content = state.content,
        onOpenNewWord = { newWord ->
            viewModel.onIntent(MainIntent.OpenNewWord(newWord))
        }
    )
}

@Composable
fun MainPane(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    currentRoute: String?,
    content: MainScreenContent,
    onOpenNewWord: (newWord: String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                content.items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp),
                                painter = painterResource(id = item.image.imageRes),
                                contentDescription = item.text
                            )
                        },
                        label = { Text(item.text) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "word_list",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("word_list") {
                WordListScreen(
                    onOpenNewWord = { newWordText ->
                        onOpenNewWord(newWordText)
                    }
                )
            }
            composable("settings") {
                SettingsScreen()
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MyApplicationTheme {
        MainPane(
            modifier = Modifier,
            navController = rememberNavController(),
            currentRoute = "word_list",
            content = MainScreenContent(
                items = listOf(
                    MainScreenContent.Item(
                        text = "Words",
                        image = Image(R.drawable.ic_word_list),
                        route = "word_list"
                    ),
                    MainScreenContent.Item(
                        text = "Words",
                        image = Image(R.drawable.ic_settings),
                        route = "word_list"
                    )
                )
            ),
            onOpenNewWord = {}
        )
    }
}