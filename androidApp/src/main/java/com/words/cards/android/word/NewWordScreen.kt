package com.words.cards.android.word

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.intent.WordListIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewWordScreen(
    modifier: Modifier = Modifier,
    newWord: String,
) {
    val viewModel = koinViewModel<WordViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(WordIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (val event = state.event) {

        }
    }

    NewWordPane(
        modifier = modifier.fillMaxSize(),
        newWord = newWord
    )
}

@Composable
fun NewWordPane(
    modifier: Modifier = Modifier,
    newWord: String,
) {
    Log.d("NewWordPane", "newWord $newWord")
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = newWord,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "ошеломленный",
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "[ˈflæbəɡæstəd]",
        )
    }
}

@Preview
@Composable
private fun NewWordPanePreview() {
    NewWordPane(
        modifier = Modifier,
        newWord = "Flabbergasted"
    )
}