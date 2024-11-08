import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import com.example.tooltiptutorialapp.core.ui.TextBubbleShape
import com.example.tooltiptutorialapp.ui.theme.OnboardingOverlayColor
import kotlinx.coroutines.launch

@Composable
fun CustomFullScreenOverlayWithTooltip(
    // cutout data
    onDismiss: () -> Unit,
    xOffset: Float = 0f,
    yOffset: Float = 0f,
    rectWidth: Float = 200f,
    rectHeight: Float = 180f,
    outerCornerRadius: Dp = 16.dp,
    innerCornerRadius: Dp = 16.dp,

    // tooltip data
    tooltipTitle: String = "Default Tooltip Text",
    backgroundColor: Color = OnboardingOverlayColor,
    titleTextColor: Color = White,
    abovePercent: Float = 0.7f
) {
    val density = LocalDensity.current

    // Calculate tooltip offset based on the rectangle size and the 40% adjustment
    val tooltipOffset = Offset(xOffset, yOffset - (rectHeight * 0.4f))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White.copy(0f)) // Semi-transparent background
            .clickable(onClick = onDismiss) // Dismiss on click
    ) {
        // Tooltip overlay at calculated offset
        RichTooltip(
            offset = tooltipOffset,
            tooltipTitle = tooltipTitle,
            backgroundColor = backgroundColor,
            titleTextColor = titleTextColor,
            abovePercent = abovePercent
        ) { isDismissed ->
            if (isDismissed) {
                onDismiss()
            }
        }

        Canvas(modifier = Modifier.matchParentSize()) {
            val outerCornerPx = with(density) { outerCornerRadius.toPx() }
            val innerCornerPx = with(density) { innerCornerRadius.toPx() }
            val rectSize = Size(rectWidth, rectHeight) // Rectangle size for overlay

            val rect = Rect(
                offset = Offset(x = xOffset, y = yOffset),
                size = rectSize
            )

            val clippedPath = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect,
                        topLeft = CornerRadius(innerCornerPx, innerCornerPx),
                        topRight = CornerRadius(innerCornerPx, innerCornerPx),
                        bottomLeft = CornerRadius(outerCornerPx, outerCornerPx),
                        bottomRight = CornerRadius(outerCornerPx, outerCornerPx)
                    )
                )
            }

            clipPath(clippedPath, clipOp = ClipOp.Difference) {
                drawRoundRect(
                    color = Black.copy(alpha = 0.5f),
                    size = Size(size.width, size.height),
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltip(
    offset: Offset,
    tooltipTitle: String,
    backgroundColor: Color,
    titleTextColor: Color,
    abovePercent: Float = 0.7f,
    onTooltipDismissed: (Boolean) -> Unit // Callback for when the tooltip is dismissed){}){}
) {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    var tooltipSize by remember{ mutableStateOf(IntSize.Zero) }

    // Trigger the tooltip when the screen is first composed
    LaunchedEffect(Unit) {
        scope.launch {
            tooltipState.show()
        }
    }

    // Observe the tooltip visibility state
    LaunchedEffect(tooltipState.isVisible) {
        scope.launch {
            if (!tooltipState.isVisible) {
                onTooltipDismissed(true)
            }
        }
    }

    TooltipBox(
        positionProvider = CustomOffsetPositionProvider(
            offset.x.toInt(),
            offset.y.toInt(),
            abovePercent = abovePercent
        ),
        tooltip = {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .onGloballyPositioned { layoutCoordinates ->
                        tooltipSize = layoutCoordinates.size
                    }
                    .clip(TextBubbleShape())
                    .background(color = backgroundColor),
            ) {
                Column(modifier = Modifier.padding(16.dp).padding(bottom = 8.dp)) {
                    Text(tooltipTitle, color = titleTextColor)
                }
            }
        },
        state = tooltipState
    ) {
        // Additional content if needed
    }
}



class CustomOffsetPositionProvider(
    private val offsetX: Int,
    private val offsetY: Int,
    private val abovePercent: Float = 0.7f // Adjust this value to control the position
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        // Calculate the position based on the anchor bounds and offsets
        val x = anchorBounds.left + offsetX
        // Position the tooltip 40% above the y offset
        val y = anchorBounds.top + offsetY - (popupContentSize.height * abovePercent).toInt()
        return IntOffset(x, y)
    }
}



