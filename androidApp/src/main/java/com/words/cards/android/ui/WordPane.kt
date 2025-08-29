package com.words.cards.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WordPane(
    modifier: Modifier = Modifier,
    word: String,
    translation: String,
    transcription: String,
    description: String,
    exampleList: String,
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(8.dp))
        WordWithTranscription(
            word = word,
            transcription = transcription
        )
        Spacer(Modifier.height(16.dp))
        Word(
            text = translation,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(16.dp))
        Word(
            text = description,
            fontWeight = FontWeight.W200,
        )
        Spacer(Modifier.height(16.dp))
        Word(
            text = exampleList,
            fontWeight = FontWeight.W200,
        )
    }
}