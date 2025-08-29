package com.words.cards.android.design

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.words.cards.android.R

@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    borderColor: Color = MaterialTheme.colorScheme.tertiary,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.brand_color),
            contentColor = colorResource(R.color.light_text)
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = text,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun CardButtonPreview() {
    CardButton(
        text = "Login",
        onClick = {},
    )
}