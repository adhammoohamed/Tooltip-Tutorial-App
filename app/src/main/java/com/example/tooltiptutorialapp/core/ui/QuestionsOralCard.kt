package com.example.tooltiptutorialapp.core.ui

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.tooltiptutorialapp.util.utility_model.OralModel

@Composable
fun QuestionsOralCard(model: OralModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.elevatedCardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Text(modifier = Modifier.padding(4.dp), text = model.type, fontSize = 8.sp)
                }

                Spacer(Modifier.width(6.dp))
                Card(
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.elevatedCardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Text(modifier = Modifier.padding(4.dp), text = "Tasks ${model.tasksNumber}", fontSize = 8.sp)
                }

                Spacer(Modifier.weight(1f))

                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(R.drawable.ic_more), contentDescription = ""
                )
            }

            Spacer(Modifier.height(4.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(".", fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(4.dp))
                Text(text = model.firstQuestion)
            }
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(".", fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(4.dp))
                Text(text = model.secondQuestion)
            }

            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(R.drawable.ic_cheat),
                    contentDescription = "",
                    tint = Color.LightGray
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "${model.answersNumber} answers",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${model.date}",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionOralCardPreview() {
    Column(Modifier.fillMaxSize()) {
        QuestionsOralCard(
            OralModel(
                tasksNumber = 8,
                type = "Type H",
                answersNumber = "4",
                firstQuestion = "What is your biggest achievement?How did you achieve it?How did you achieve it?How did you achieve it?How did you achieve it?\nHow did you achieve it?How did you achieve it?",
                secondQuestion = "How did you achieve it\nWhat is your biggest achievement?What is your biggest achievement?What is your biggest achievement?What is your biggest achievement?What is your biggest achievement??",
                date = "2024-11-08"
            )
        )
    }
}
