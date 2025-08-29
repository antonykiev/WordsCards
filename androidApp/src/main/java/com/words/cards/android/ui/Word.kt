package com.words.cards.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.words.cards.android.R

@Composable
fun Word(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight,
) {
    Box(
        modifier = modifier
            .background(
                color = colorResource(R.color.brand_color).copy(alpha = 0.1F),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 18.sp,
                color = colorResource(R.color.brand_color),
                fontWeight = fontWeight,
            )
        )
    }
}