package com.words.cards.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.words.cards.android.R

@Composable
fun WordWithTranscription(
    modifier: Modifier = Modifier,
    word: String,
    transcription: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = colorResource(R.color.brand_color).copy(alpha = 0.1F),
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = word,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(R.color.brand_color),
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = transcription,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(R.color.brand_color),
                    fontWeight = FontWeight.Light,
                )
            )
        }
    }
}