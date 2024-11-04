package com.example.tooltiptutorialapp.screens.questions

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.core.ui.QuestionWritingModel
import com.example.tooltiptutorialapp.core.ui.QuestionsOralCard
import com.example.tooltiptutorialapp.core.ui.ScreenHeader
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.ui.theme.ThirdColor
import com.example.tooltiptutorialapp.ui.theme.TransparentSecondColor
import com.example.tooltiptutorialapp.util.utility_model.oralModels
import com.example.tooltiptutorialapp.util.utility_model.writingModels

@Composable
fun QuestionsScreen(modifier: Modifier) {
    val viewModel: QuestionsViewModel = viewModel()
    val pages = listOf("Writing 1", "Oral")
    val icons = listOf(R.drawable.ic_pen, R.drawable.ic_microphone)
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp),
    ) {
        ScreenHeader("Questions")

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .width(300.dp)
                .padding(horizontal = 16.dp),
        ) {
            pages.forEachIndexed { index, pageTitle ->
                Tab(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .zIndex(6f),
                    selectedContentColor = SecondColor,
                    unselectedContentColor = Color.LightGray,
                    text = {
                        Row {
                            Icon(
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Alignment.CenterVertically),
                                painter = painterResource(icons[index]),
                                contentDescription = null,
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .padding(horizontal = 5.dp, vertical = 10.dp),
                                text = pageTitle,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                )
            }
        }

        Button(
            modifier = Modifier.padding(top = 16.dp , start = 16.dp),
            onClick = { /* Add button action here */ },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TransparentSecondColor)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .padding(horizontal = 5.dp, vertical = 10.dp),
                    text = "Filter",
                    textAlign = TextAlign.Center,
                    color = ThirdColor
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_menu),
                    contentDescription = "Filter",
                    tint = Color.Black
                )
            }
        }

        // Content area below the button with LazyGrid and LazyColumn based on the selected tab
        if (selectedTabIndex == 0) {
            Log.d("QuestionsScreen", "LazyVerticalGrid is being displayed")
            Log.d("QuestionsScreen", writingModels.size.toString())
            Log.d("QuestionsScreen", writingModels.toString())

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(writingModels) { model ->
                    QuestionWritingModel(model)
                }
            }
        } else {
            // Oral tab - LazyColumn
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {


                items(oralModels) {
                    QuestionsOralCard(it)
                }
            }
        }
    }
}

