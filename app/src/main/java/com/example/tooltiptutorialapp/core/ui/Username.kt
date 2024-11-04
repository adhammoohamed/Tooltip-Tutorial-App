package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tooltiptutorialapp.ui.theme.OnboardingOverlayColor

@Composable
fun Username(name: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Hi",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = OnboardingOverlayColor,
            ),

        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = name,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = OnboardingOverlayColor,
            ),
            fontWeight = FontWeight.Bold
        )
    }
}