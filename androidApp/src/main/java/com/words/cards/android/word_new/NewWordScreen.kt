package com.words.cards.android.word_new

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.R
import com.words.cards.android.design.CardButton
import com.words.cards.android.ui.WordPane
import com.words.cards.presentation.event.NewWordEvent
import com.words.cards.presentation.intent.NewWordIntent
import com.words.cards.presentation.state.LoadableContent
import com.words.cards.presentation.state.NewWordScreenContent
import com.words.cards.presentation.state.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewWordScreen(
    modifier: Modifier,
    newWord: String,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)

    val viewModel = koinViewModel<NewWordViewModel>()
    val state: State<NewWordScreenContent, NewWordEvent> by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(NewWordIntent.InitialLoad(newWord))
    }

    LaunchedEffect(state.event) {
        when (val event: NewWordEvent = state.event) {
            NewWordEvent.Empty -> {}
            NewWordEvent.Saved -> {
                onBack()
            }
        }
        viewModel.onEventHandled(state.event)
    }

    NewWordPane(
        modifier = modifier,
        content = state.content,
        onSaveClicked = {
            viewModel.onIntent(NewWordIntent.OnSaveClicked)
        }
    )
}

@Composable
fun NewWordPane(
    modifier: Modifier = Modifier,
    content: NewWordScreenContent,
    onSaveClicked: () -> Unit,
) {
    if (content.isLoading) {
        Box(
            modifier = modifier
                .background(
                    color = colorResource(R.color.background)
                )
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(R.color.brand_color),
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round
            )
        }
    } else {
        Box(
            modifier = modifier
        ) {
            WordPane(
                modifier = modifier,
                word = content.word,
                translation = content.translation,
                transcription = content.transcription,
                description = content.description,
                exampleList = content.exampleList
            )

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
}

@Preview
@Composable
private fun NewWordPanePreview() {
    NewWordPane(
        modifier = Modifier,
        content = NewWordScreenContent(
            word = "flabbergasted",
            isLoading = false,
            transcription = "ˈflæbərˌɡæstɪd",
            exampleList = "I was flabbergasted by the news of winning the lottery.",
            translation = "ошеломленный",
            image = LoadableContent.Loading,
            description = "flabbergasted means to be extremely surprised or shocked."
        ),
        onSaveClicked = {}
    )
}

@Preview
@Composable
private fun NewWordPaneLoadingPreview() {
    NewWordPane(
        modifier = Modifier,
        content = NewWordScreenContent(
            word = "flabbergasted",
            isLoading = true,
            transcription = "ˈflæbərˌɡæstɪd",
            exampleList = "I was flabbergasted by the news of winning the lottery.",
            translation = "ошеломленный",
            image = LoadableContent.Loading,
            description = "flabbergasted means to be extremely surprised or shocked."
        ),
        onSaveClicked = {}
    )
}