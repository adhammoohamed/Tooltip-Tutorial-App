package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tooltiptutorialapp.ui.theme.OnboardingOverlayColor
import com.example.tooltiptutorialapp.ui.theme.SecondColor

@Composable
fun OnboardingOverlay(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnboardingOverlayColor.copy(alpha = 0.7f)) // Semi-transparent gray color
            .clickable { onDismiss() } // Dismiss overlay on tap
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "welcome to: how to use and enjoy\nExamate",
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Tap anywhere on the screen to\ncontinue",
                color = SecondColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
    }
}

