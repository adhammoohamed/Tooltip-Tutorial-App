package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tooltiptutorialapp.ui.theme.SecondColor

@Composable
fun ScreenHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium.copy(
            color = SecondColor,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 16.dp)

    )
}