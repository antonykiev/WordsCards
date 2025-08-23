package com.words.cards.android.wordlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordListIntent
import com.words.cards.presentation.state.WordItem
import com.words.cards.presentation.state.WordListScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun WordListScreen(
    modifier: Modifier = Modifier,
   onOpenNewWord: (word: String) -> Unit
) {

    val viewModel = koinViewModel<WordListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(WordListIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (val event = state.event) {
            WordListEvent.Empty -> { }
            is WordListEvent.OpenWord -> {}
            is WordListEvent.OpenNewWord -> {
                onOpenNewWord(event.word)
            }
        }
    }

    WordListPane(
        modifier = modifier,
        content = state.content,
        text = state.content.searchWord,
        onValueChange = {
            viewModel.onIntent(WordListIntent.OnSearchWordChanged(it))
        },
        onPlusClicked = {
            viewModel.onIntent(WordListIntent.OnPlusClicked(it))
        },
        onWordClicked = { wordId ->
            viewModel.onIntent(WordListIntent.OnWordClicked(wordId))
        }
    )
}

@Composable
fun WordListPane(
    modifier: Modifier = Modifier,
    content: WordListScreenContent,
    text: String,
    onValueChange: (String) -> Unit,
    onPlusClicked: (word: String) -> Unit,
    onWordClicked: (wordId: Long) -> Unit,
) {

    Column {
        SearchView(
            modifier = Modifier,
            value = text,
            onValueChange = onValueChange,
            onPlusClicked = onPlusClicked,
        )
        LazyColumn {
            itemsIndexed(
                items = content.wordsItems,
                key = { index, item -> item.id }
            ) { index, item ->
                WordItem(
                    modifier = Modifier,
                    item = item,
                    onWordClicked = onWordClicked
                )
            }
        }
    }
}

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    onPlusClicked: (newWord: String) -> Unit = {},
    placeholder: String = "Type something...",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder?.let { { Text(it) } },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                ) {

                }
            }
        )

        AnimatedVisibility(
            visible = value.isNotEmpty(),
            enter = fadeIn(animationSpec = tween(durationMillis = 150)) +
                    slideInHorizontally(animationSpec = tween(durationMillis = 200)) { it / 2 },
            exit = fadeOut(animationSpec = tween(durationMillis = 150)) +
                    slideOutHorizontally(animationSpec = tween(durationMillis = 200)) { it / 2 },
            modifier = Modifier.padding(end = 8.dp)
        ) {
            IconButton(
                onClick = {
                    onPlusClicked(value)
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    item: WordItem,
    onWordClicked: (wordId: Long) -> Unit,
) {

}

@Preview
@Composable
private fun SearchViewPreview() {
    SearchView(
        value = ""
    )
}