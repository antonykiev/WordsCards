package com.words.cards.android.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.MyApplicationTheme
import com.words.cards.android.design.Logo
import com.words.cards.presentation.event.SplashEvent
import com.words.cards.presentation.intent.SplashIntent
import com.words.cards.presentation.state.SplashScreenContent
import com.words.cards.presentation.state.UserLoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onSuccessfullyLoaded: () -> Unit,
) {
    val viewModel = koinViewModel<SplashViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.onIntent(SplashIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (val event = state.event) {
            SplashEvent.Empty -> {

            }

            SplashEvent.LoadedSuccessfully -> {
                viewModel.onEventHandled(event)
                onSuccessfullyLoaded()
            }

            SplashEvent.LoadedWithError -> {

            }
        }
    }

    SplashPane(
        modifier = modifier,
        content = state.content
    )
}

@Composable
fun SplashPane(
    modifier: Modifier = Modifier,
    content: SplashScreenContent,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Logo()
    }
}

@Preview
@Composable
private fun SplashPanePreview() {
    MyApplicationTheme {
        SplashPane(
            content = SplashScreenContent(
                userLoginState = UserLoginState.Initial
            )
        )
    }
}

