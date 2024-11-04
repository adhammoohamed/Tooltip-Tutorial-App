package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.ui.theme.ThirdColor
import com.example.tooltiptutorialapp.ui.theme.TransparentSecondColor
import com.example.tooltiptutorialapp.util.utility_model.StudyPartnerModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestionCard(item: StudyPartnerModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        val names = item.name.split(" ")
        val firstName = names[0]
        val secondName = names[1]

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(SecondColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = firstName[0].toString() + secondName[0].toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                }

                Spacer(Modifier.width(8.dp))


                Column {

                    Row {
                        Text(
                            text = item.name,
                            color = ThirdColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )

                        Spacer(Modifier.width(8.dp))

                        Card(
                            modifier = Modifier.wrapContentHeight(),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
                            colors = CardDefaults.cardColors(containerColor = SecondColor)
                        ) {
                            Text(
                                "Targeting: ${item.targetingLevel}",
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(3.dp)
                            )
                        }
                    }

                    Text(
                        text = "Last Seen Online: ${item.lastSeen}",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )

                    FlowRow(
                        maxItemsInEachRow = 3,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item.spokenLanguages.forEach {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = TransparentSecondColor),
                                shape = RectangleShape
                            ) {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = it,
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DataIcon(Icons.Outlined.LocationOn, "Egypt")
                DataIcon(painterResource(R.drawable.femenine), item.gender)
                DataIcon(painterResource(R.drawable.cake), item.age.toString())
                DataIcon(Icons.Outlined.DateRange, "21 June 2023")
            }
        }
    }
}


@Composable
fun DataIcon(icon: ImageVector, text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = "Location",
            modifier = Modifier.size(20.dp)

        )

        Text(text = text, color = Color.Gray, fontSize = 12.sp)
    }
}

@Composable
fun DataIcon(icon: Painter, text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Icon(
            painter = icon,
            contentDescription = "Location",
            modifier = Modifier.size(20.dp)

        )

        Text(text = text, color = Color.Gray, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSuggestionCard() {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            SuggestionCard(
                StudyPartnerModel(
                    name = "Reem Sayed",
                    lastSeen = "Yesterday",
                    spokenLanguages = listOf("Arabic", "English", "French"),
                    location = "Egypt",
                    gender = "Female",
                    age = 25,
                    joinedDate = "2022-01-01",
                    targetingLevel = "B1"
                )
            )
        }
    }
}