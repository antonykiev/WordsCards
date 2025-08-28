package com.words.cards.android.word

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.state.LoadableContent
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
            WordEvent.Empty -> { /* No-op */
            }
        }
        viewModel.onEventHandled(state.event)
    }

    WordPane(
        modifier = modifier,
        content = state.content,
    )
}

@Composable
fun WordPane(
    modifier: Modifier = Modifier,
    content: WordScreenContent
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        color = Color(0xFF1A4EEC).copy(alpha = 0.1F),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    content.word,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xFF1A4EEC),
                        fontWeight = FontWeight.Bold,
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    content.transcription,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xFF1A4EEC),
                        fontWeight = FontWeight.Light,
                    )
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF1A4EEC).copy(alpha = 0.1F),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                content.translation,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF1A4EEC),
                    fontWeight = FontWeight.Bold,
                )
            )
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF1A4EEC).copy(alpha = 0.1F),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                content.description,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF1A4EEC),
                    fontWeight = FontWeight.W200,
                )
            )
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF1A4EEC).copy(alpha = 0.1F),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                content.exampleList,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFF1A4EEC),
                    fontWeight = FontWeight.W200,
                )
            )
        }
    }
}

@Preview
@Composable
private fun WordPanePreview() {
    WordPane(
        content = WordScreenContent(
            word = "flabbergasted",
            description = "\uD83D\uDE32 Flabbergasted is an adjective used to describe someone who is extremely surprised or shocked, often to the point of being speechless.",
            translation = "ошеломлённый",
            transcription = "[ˈflæbərˌɡæstɪd]",
            exampleList = "- “I was flabbergasted when she announced she was quitting her job to become a trapeze artist.”\n" +
                    "- “The fans were flabbergasted by the unexpected win.\n",
            image = LoadableContent.Loading
        )
    )
}