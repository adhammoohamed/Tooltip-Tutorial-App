package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.util.utility_model.UnitItem

@Composable
fun UnitCircleView(unitItem: UnitItem, index: Int, unitListSize: Int) {


    UnitItem(
        unitNumber = unitItem.unitNumber,
        unitTitle = "Unite ${unitItem.unitNumber}:",
        isActive = index == 0,
        unitDescription = unitItem.unitTitle
    )

    if (index < unitListSize - 1) {
        Canvas(
            modifier = Modifier
                .width(131.dp)
                .height(50.dp)
                .padding(vertical = 4.dp)
        ) {
            drawLine(
                color = if (index == 0) SecondColor else Color.LightGray,
                start = center.copy(y = 0f),
                end = center.copy(y = size.height),
                strokeWidth = 50f
            )
        }
    }
}


@Composable
fun UnitItem(
    unitNumber: String,
    unitTitle: String,
    unitDescription: String,
    isActive: Boolean = false,
    strokeWidth: Float = 4f
) {

    val circleColor = if (isActive) SecondColor else Color.LightGray
    val strokeColor = if (isActive) SecondColor else Color.LightGray
    val textColor = if (isActive) SecondColor else Color.Gray

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(100.dp)
        ) {

            Box {

                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = strokeColor,
                        radius = (size.minDimension / 2),
                        style = Stroke(width = 25f)
                    )
                }

                if (!isActive) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.LightGray, shape = CircleShape)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Lock",
                            tint = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )

                    }
                }
            }

            Canvas(modifier = Modifier.size(60.dp + strokeWidth.dp)) {
                drawCircle(
                    color = strokeColor,
                    radius = (size.minDimension / 2),
                    style = Stroke(width = strokeWidth)
                )
            }

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(circleColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = unitNumber,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

            }

        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = unitTitle,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = unitDescription,
                color = textColor,
                fontSize = 14.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUnitCircleView() {
    MaterialTheme {
        UnitCircleView(UnitItem("1", "Unit 1"), 0, 10)
    }
}