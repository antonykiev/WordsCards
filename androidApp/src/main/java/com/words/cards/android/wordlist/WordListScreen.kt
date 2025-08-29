package com.words.cards.android.wordlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.R
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordListIntent
import com.words.cards.presentation.state.WordItem
import com.words.cards.presentation.state.WordListScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun WordListScreen(
    modifier: Modifier = Modifier,
    onOpenNewWord: (word: String) -> Unit,
    onOpenWord: (wordId: Long) -> Unit,
) {
    val viewModel = koinViewModel<WordListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(WordListIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (val event = state.event) {
            WordListEvent.Empty -> {}
            is WordListEvent.OpenWord -> {
                onOpenWord(event.wordId)
            }

            is WordListEvent.OpenNewWord -> {
                onOpenNewWord(event.word)
            }
        }
        viewModel.onEventHandled(state.event)
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

    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.brand_color),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(6.dp))
            SearchView(
                modifier = Modifier,
                value = text,
                onValueChange = onValueChange,
                onPlusClicked = onPlusClicked,
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
        ) {
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
    placeholder: String = "New word",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        TextField(
            value = value,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            ),
            shape = RoundedCornerShape(28.dp),
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.brand_color),
                unfocusedContainerColor = colorResource(R.color.brand_color),
                disabledContainerColor = colorResource(R.color.brand_color),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White.copy(alpha = 0.8f),
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
            modifier = Modifier
                .padding(end = 8.dp),
            visible = value.isNotEmpty(),
            enter = fadeIn(animationSpec = tween(durationMillis = 150)) +
                    slideInHorizontally(animationSpec = tween(durationMillis = 200)) { it / 2 },
            exit = fadeOut(animationSpec = tween(durationMillis = 150)) +
                    slideOutHorizontally(animationSpec = tween(durationMillis = 200)) { it / 2 },
        ) {
            IconButton(
                modifier = Modifier.padding(start = 8.dp),
                onClick = {
                    onPlusClicked(value)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.brand_color).copy(alpha = 0.1F),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 16.dp
            )
            .clickable {
                onWordClicked(item.id)
            }
    ) {
        Row {
            Text(
                item.word,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Spacer(Modifier.width(8.dp))
            Text(
                item.transcription,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(item.description)
    }

}

@Preview
@Composable
private fun SearchViewPreview() {
    SearchView(
        value = ""
    )
}

@Preview
@Composable
private fun WordListPanePreview() {
    WordListPane(
        content = WordListScreenContent(
            searchWord = "flabbergasted",
            wordsItems = listOf(
                WordItem(
                    word = "flabbergasted",
                    translation = "ошеломленный",
                    transcription = "[flæbərɡæstɪd]",
                    description = "very surprised",
                    id = 1
                ),
                WordItem(
                    word = "flabbergasted",
                    translation = "ошеломленный",
                    transcription = "[flæbərɡæstɪd]",
                    description = "very surprised",
                    id = 2
                ),
                WordItem(
                    word = "flabbergasted",
                    translation = "ошеломленный",
                    transcription = "[flæbərɡæstɪd]",
                    description = "very surprised",
                    id = 3
                )
            )
        ),
        text = "",
        onValueChange = { },
        onPlusClicked = { },
        onWordClicked = { }
    )
}