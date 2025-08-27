package com.words.cards.android.word_new

import androidx.activity.compose.BackHandler
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
import com.words.cards.android.MyApplicationTheme
import com.words.cards.android.design.CardButton
import com.words.cards.presentation.event.WordNewEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.LoadableContent
import com.words.cards.presentation.state.WordScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewWordScreen(
    modifier: Modifier = Modifier,
    newWord: String,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    val viewModel = koinViewModel<NewWordViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(WordIntent.InitialLoad(newWord))
    }

    LaunchedEffect(state.event) {
        when (val event: WordNewEvent = state.event) {
            WordNewEvent.Empty -> {}
            WordNewEvent.Saved -> {
                onBack()
            }
        }
            viewModel.onEventHandled(state.event)
    }

    NewWordPane(
        modifier = modifier.fillMaxSize(),
        content = state.content,
        onSaveClicked = {
            viewModel.onIntent(WordIntent.OnSaveClicked)
        }
    )
}

@Composable
fun NewWordPane(
    modifier: Modifier = Modifier,
    content: WordScreenContent,
    onSaveClicked: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = content.word,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Description:",
            )
            Text(
                text = content.description,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Transcription:",
            )
            Text(
                text = content.transcription,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Translation:",
            )
            Text(
                text = content.translation,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Examples:",
            )
            Text(
                text = content.exampleList,
            )
        }

        CardButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
            text = "Save",
            onClick = onSaveClicked
        )
    }
}

@Preview
@Composable
private fun NewWordPanePreview() {
    MyApplicationTheme {
        NewWordPane(
            modifier = Modifier,
            content = WordScreenContent(
                word = "flabbergasted",
                transcription = "ˈflæbərˌɡæstɪd",
                exampleList = "I was flabbergasted by the news of winning the lottery.",
                translation = "ошеломленный",
                image = LoadableContent.Loading,
                description = "flabbergasted means to be extremely surprised or shocked."
            ),
            onSaveClicked = {}
        )
    }
}