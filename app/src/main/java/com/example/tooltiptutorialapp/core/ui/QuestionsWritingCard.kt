package com.example.tooltiptutorialapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.ui.theme.ThirdColor
import com.example.tooltiptutorialapp.ui.theme.TransparentSecondColor
import com.example.tooltiptutorialapp.util.utility_model.WritingModel

@Composable
fun QuestionWritingModel(model: WritingModel) {

    Card(
        modifier = Modifier
            .height(160.dp)
            .wrapContentWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
    ) {

        Column {
            Card(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(containerColor = TransparentSecondColor)
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "${model.answeredQuestion} sur 10 Questions",
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }

            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.ic_airplane),
                    contentDescription = ""
                )

                Text(text = model.title, fontWeight = FontWeight.Bold, color = ThirdColor)
            }

            Text(modifier = Modifier.padding(8.dp), text = "Progress ${model.progress}%")

            LinearProgressIndicator(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                progress = model.progress.toFloat() / 100,
                color = SecondColor,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionWritingModelPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        QuestionWritingModel(
            model = WritingModel(
                title = "Technologie",
                answeredQuestion = 10,
                progress = 50
            )
        )
    }
}