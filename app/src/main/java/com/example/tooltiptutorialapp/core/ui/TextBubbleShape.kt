package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.tooltiptutorialapp.ui.theme.OnboardingOverlayColor

class TextBubbleShape(
    private val cornerRadius: Dp = 15.dp,
    private val tipSize: Dp = 15.dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val tipSizePx = with(density) { tipSize.toPx() }
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val path = Path().apply {
            // Draw the rounded rectangle
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height - tipSizePx,
                    radiusX = cornerRadiusPx,
                    radiusY = cornerRadiusPx
                )
            )

            // Draw the arrow at the bottom center
            moveTo(size.width / 7 - tipSizePx, size.height - tipSizePx)
            lineTo(size.width / 6.8f, size.height)
            lineTo(size.width / 6.8f + tipSizePx, size.height - tipSizePx)
            close()
        }
        return Outline.Generic(path)
    }
}


@Preview(showBackground = true)
@Composable
fun TextBubbleShapePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .onGloballyPositioned { layoutCoordinates ->
                }
                .clip(TextBubbleShape())
                .background(color = OnboardingOverlayColor),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("tooltipTitlrrrrrrrrrrrrrrrrrrre", color = Color.White)
                Text("tooltipText", color = Color.White)
            }
        }
    }
}
