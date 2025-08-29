package com.words.cards.android.word

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.ui.WordPane
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
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
            WordEvent.Empty -> { /* No-op */
            }
        }
        viewModel.onEventHandled(state.event)
    }

    WordPane(
        modifier = modifier,
        word = state.content.word,
        translation = state.content.translation,
        transcription = state.content.transcription,
        description = state.content.description,
        exampleList = state.content.exampleList
    )
}

@Preview
@Composable
private fun WordPanePreview() {
    WordPane(
        word = "flabbergasted",
        description = "\uD83D\uDE32 Flabbergasted is an adjective used to describe someone who is extremely surprised or shocked, often to the point of being speechless.",
        translation = "ошеломлённый",
        transcription = "[ˈflæbərˌɡæstɪd]",
        exampleList = "- “I was flabbergasted when she announced she was quitting her job to become a trapeze artist.”\n" +
                "- “The fans were flabbergasted by the unexpected win.\n",
    )
}