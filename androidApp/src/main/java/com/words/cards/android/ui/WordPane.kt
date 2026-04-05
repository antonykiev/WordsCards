package com.words.cards.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.words.cards.android.R

@Composable
fun WordPane(
    modifier: Modifier = Modifier,
    word: String,
    translation: String,
    transcription: String,
    description: String,
    exampleList: String,
    onBackClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.brand_color).copy(alpha = 0.1F),
                        shape = RoundedCornerShape(28.dp)
                    ),
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = colorResource(R.color.brand_color)
                )
            }

            WordWithTranscription(
                word = word,
                transcription = transcription
            )
        }

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