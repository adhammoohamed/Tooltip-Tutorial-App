package com.example.tooltiptutorialapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.ui.theme.SelectedIconColor
import com.example.tooltiptutorialapp.ui.theme.TooltipTutorialAppTheme
import com.example.tooltiptutorialapp.util.utility_model.BottomNavigationItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TooltipTutorialAppTheme {
                val bottomNavigationItems = listOf(
                    BottomNavigationItem(
                        label = "Home",
                        selectedIcon = painterResource(id = R.drawable.ic_selected_home),
                        unselectedIcon = painterResource(id = R.drawable.ic_unselected_home),
                        route = "home"
                    ),
                    BottomNavigationItem(
                        label = "Connect",
                        selectedIcon = painterResource(id = R.drawable.ic_selected_connect),
                        unselectedIcon = painterResource(id = R.drawable.ic_unselected_connect),
                        route = "home"
                    ),
                    BottomNavigationItem(
                        label = "Questions",
                        selectedIcon = painterResource(id = R.drawable.ic_selected_questions),
                        unselectedIcon = painterResource(id = R.drawable.ic_unselected_questions),
                        route = "home"
                    ),
                    BottomNavigationItem(
                        label = "Tools",
                        selectedIcon = painterResource(id = R.drawable.ic_selected_tools),
                        unselectedIcon = painterResource(id = R.drawable.ic_unselected_tools),
                        route = "home"
                    ),
                    BottomNavigationItem(
                        label = "Profile",
                        selectedIcon = painterResource(id = R.drawable.ic_selected_profile),
                        unselectedIcon = painterResource(id = R.drawable.ic_unselected_profile),
                        route = "home"
                    )

                )


                var selectedIndexItem by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            bottomNavigationItems.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedIndexItem == index,
                                    onClick = {
                                        selectedIndexItem = index
                                    },
                                    label = {
                                        Text(
                                            text = item.label,
                                            fontSize = 10.sp,
                                            fontWeight = if (selectedIndexItem == index) FontWeight.Bold else FontWeight.Normal,
                                            color = if (selectedIndexItem == index) SelectedIconColor else Black
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            painter = if (selectedIndexItem == index) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.label,
                                            modifier = Modifier.size(20.dp),
                                            tint = if (selectedIndexItem == index) SelectedIconColor else Black
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                    }

                }
            }
        }
    }
}
