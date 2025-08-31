package com.words.cards.android.word

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.R
import com.words.cards.android.design.CardButton
import com.words.cards.android.ui.WordPane
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.WordScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun WordScreen(
    modifier: Modifier = Modifier,
    wordId: Long,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)

    val viewModel = koinViewModel<WordViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(WordIntent.InitialLoad(wordId = wordId))
    }

    LaunchedEffect(state.event) {
        when (val event: WordEvent = state.event) {
            WordEvent.Empty -> {
                /* No-op */
            }

            WordEvent.GoBack -> {
                onBack()
            }
        }
        viewModel.onEventHandled(state.event)
    }

    WordPaneContent(
        modifier = modifier,
        content = state.content,
        onDeleteClicked = { wordId ->
            viewModel.onIntent(WordIntent.DeleteWord(wordId = wordId))
        }
    )


}

@Composable
fun WordPaneContent(
    modifier: Modifier = Modifier,
    content: WordScreenContent,
    onDeleteClicked: (Long) -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        WordPane(
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
            backgroundColor = colorResource(R.color.error_color),
            text = "Delete the word",
            onClick = {
                onDeleteClicked(content.wordId)
            }
        )
    }
}

@Preview
@Composable
private fun WordPaneContentPreview() {
    WordPaneContent(
        modifier = Modifier,
        onDeleteClicked = {},
        content = WordScreenContent(
            wordId = 1,
            word = "flabbergasted",
            description = "\uD83D\uDE32 Flabbergasted is an adjective used to describe someone who is extremely surprised or shocked, often to the point of being speechless.",
            translation = "ошеломлённый",
            transcription = "[ˈflæbərˌɡæstɪd]",
            exampleList = "- “I was flabbergasted when she announced she was quitting her job to become a trapeze artist.”\n" +
                    "- “The fans were flabbergasted by the unexpected win.\n",
        )
    )
}