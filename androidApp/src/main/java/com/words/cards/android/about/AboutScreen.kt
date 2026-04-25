package com.words.cards.android.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.design.CardButton
import com.words.cards.android.design.Logo
import com.words.cards.presentation.event.AboutEvent
import com.words.cards.presentation.intent.AboutIntent
import com.words.cards.presentation.state.AboutScreenContent
import com.words.cards.presentation.state.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    val viewModel: AboutViewModel = koinViewModel<AboutViewModel>()
    val state: State<AboutScreenContent, AboutEvent> by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(AboutIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (state.event) {
            AboutEvent.Empty -> Unit
            AboutEvent.GoBack -> {
                onBack()
                viewModel.onEventHandled(AboutEvent.GoBack)
            }
        }
    }

    AboutPane(
        modifier = modifier,
        content = state.content,
        onBackClick = {
            viewModel.onIntent(AboutIntent.OnBackClicked)
        }
    )
}

@Composable
fun AboutPane(
    modifier: Modifier = Modifier,
    content: AboutScreenContent,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo()
            Spacer(Modifier.height(24.dp))
            Text(
                text = content.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = content.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
        ) {
            CardButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Back",
                onClick = onBackClick
            )
        }
    }
}

@Preview
@Composable
private fun AboutPanePreview() {
    AboutPane(
        content = AboutScreenContent(
            title = "About Words Cards",
            description = "Words Cards is an app designed to help you learn new vocabulary using AI."
        ),
        onBackClick = {}
    )
}
