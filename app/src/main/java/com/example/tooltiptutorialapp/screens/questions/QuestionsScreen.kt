package com.example.tooltiptutorialapp.screens.questions

import androidx.compose.animation.AnimatedVisibility
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
fun QuestionsScreen(
    modifier: Modifier,
    viewModel: QuestionsViewModel = viewModel() // Dependency injection for ViewModel
) {
    val tabTitles = listOf("Writing 1", "Oral")
    val tabIcons = listOf(R.drawable.ic_pen, R.drawable.ic_microphone)
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .wrapContentSize()
    ) {
        ScreenHeader("Questions")

        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .width(300.dp)
                .padding(horizontal = 16.dp),
        ) {
            tabTitles.forEachIndexed { index, title ->
                TabWithIcon(
                    icon = tabIcons[index],
                    title = title,
                    isSelected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { /* Handle filter action */ },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TransparentSecondColor)
        ) {
            Row {
                Text(
                    text = "Filter",
                    textAlign = TextAlign.Center,
                    color = ThirdColor,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_menu),
                    contentDescription = "Filter",
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        AnimatedVisibility(visible = selectedTabIndex == 0) {
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
        }

        AnimatedVisibility(visible = selectedTabIndex == 1) {
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

@Composable
fun TabWithIcon(
    icon: Int,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Tab(
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = SecondColor,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.clip(MaterialTheme.shapes.large)
    ) {
        Row {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
            )
        }
    }
}



