package com.example.tooltiptutorialapp.core.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tooltiptutorialapp.R
import com.example.tooltiptutorialapp.screens.connect.navigateToConnectScreen
import com.example.tooltiptutorialapp.screens.home.navigateToHomeScreen
import com.example.tooltiptutorialapp.screens.profile.navigateToProfileScreen
import com.example.tooltiptutorialapp.screens.questions.navigateToQuestionsScreen
import com.example.tooltiptutorialapp.screens.tools.navigateToToolsScreen
import com.example.tooltiptutorialapp.ui.theme.SecondColor
import com.example.tooltiptutorialapp.util.utility_model.BottomNavigationItem

// Define a global list to store the sizes of the bottom navigation bar items
val bottomNavBarGlobalOffsets = mutableStateListOf<Offset>()
val bottomNavBarGlobalSizes = mutableStateListOf<IntSize>()

@Composable
fun BaseScreen(
    navController: NavController,
    selectedIndex: Int,
    overlayContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    var isOverlayVisible by remember { mutableStateOf(true) }
    var currentOffset by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(selectedIndex) {
        if (bottomNavBarGlobalOffsets.isNotEmpty()) {
            currentOffset = bottomNavBarGlobalOffsets[selectedIndex]
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content()
        CustomBottomNavBar(
            selectedIndex = selectedIndex,
            onItemSelected = { index, offset ->
                currentOffset = offset
            },
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter),
            onOffsetsCalculated = { offsets, sizes ->
                bottomNavBarGlobalOffsets.clear()
                bottomNavBarGlobalOffsets.addAll(offsets)

                bottomNavBarGlobalSizes.clear()
                bottomNavBarGlobalSizes.addAll(sizes)
            }
        )

        AnimatedVisibility(
            visible = isOverlayVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)),
            exit = fadeOut(animationSpec = tween(durationMillis = 300)),
        ) {
            overlayContent()
        }
    }
}

@Composable
fun CustomBottomNavBar(
    modifier: Modifier,
    selectedIndex: Int,
    onItemSelected: (Int, Offset) -> Unit,
    navController: NavController,
    onOffsetsCalculated: (List<Offset>, List<IntSize>) -> Unit
) {
    val items = listOf(
        BottomNavigationItem("Home", painterResource(R.drawable.ic_selected_home), painterResource(R.drawable.ic_unselected_home), { navController.navigateToHomeScreen() }),
        BottomNavigationItem("Connect", painterResource(R.drawable.ic_selected_connect), painterResource(R.drawable.ic_unselected_connect), { navController.navigateToConnectScreen() }),
        BottomNavigationItem("Questions", painterResource(R.drawable.ic_selected_questions), painterResource(R.drawable.ic_unselected_questions), { navController.navigateToQuestionsScreen() }),
        BottomNavigationItem("Tools", painterResource(R.drawable.ic_selected_tools), painterResource(R.drawable.ic_unselected_tools), { navController.navigateToToolsScreen() }),
        BottomNavigationItem("Profile", painterResource(R.drawable.ic_selected_profile), painterResource(R.drawable.ic_unselected_profile), { navController.navigateToProfileScreen() })
    )

    NavigationBar(
        containerColor = White,
        modifier = modifier.fillMaxWidth()
    ) {
        val itemOffsets = remember { MutableList(items.size) { Offset(0f, 0f) } }
        val itemSizes = remember { MutableList(items.size) { IntSize(0, 0) } }

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = White,
                    selectedIconColor = SecondColor,
                    unselectedIconColor = Black
                ),
                modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                    val offset = layoutCoordinates.localToWindow(Offset.Zero)
                    val adjustedOffset = Offset(offset.x + 8f, offset.y + 10f)
                    itemOffsets[index] = adjustedOffset

                    val size = layoutCoordinates.size
                    itemSizes[index] = size

                    if (itemOffsets.all { it != Offset(0f, 0f) } && itemSizes.all { it != IntSize(0, 0) }) {
                        onOffsetsCalculated(itemOffsets, itemSizes)
                    }
                },
                selected = selectedIndex == index,
                onClick = {
                    val offset = itemOffsets[index]
                    onItemSelected(index, offset)
                    item.navigate()
                },
                icon = {
                    Icon(
                        painter = if (selectedIndex == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(text = item.label, fontSize = 10.sp)
                }
            )
        }
    }
}
