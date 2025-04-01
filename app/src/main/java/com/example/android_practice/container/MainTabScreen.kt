package com.example.android_practice.container

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import kotlinx.parcelize.Parcelize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.example.android_practice.listWithDetails.presentation.screens.ListScreen
import com.example.android_practice.profile.presentation.screens.ProfileScreen
import com.github.terrakok.modo.animation.SlideTransition
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.selectScreen
import com.example.android_practice.ui.PurpleGrey40

@Parcelize
class MainTabScreen(
    private val navModel: MultiScreenNavModel = MultiScreenNavModel(
        ListScreen(),
        ProfileScreen(),
        selected = 0
    )
) : MultiScreen(navModel) {

    @Composable
    override fun Content(modifier: Modifier) {
        MainTabContent(
            modifier = modifier,
            selectedTabPos = navigationState.selected,
            onTabClick = { pos ->
                selectScreen(pos)
            }
        ) {
            SelectedScreen(
                Modifier
                    .padding(this)
                    .fillMaxSize()
            ) { innerModifier ->
                SlideTransition(innerModifier)
            }
        }
    }
}

@Composable
fun MainTabContent(
    selectedTabPos: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable PaddingValues.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar {
                for ((pos, tab) in MainTabs.entries.withIndex()) {
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onTabClick(pos)
                        },
                    ) {
                        val color by animateColorAsState(
                            targetValue = if (pos == selectedTabPos) {
                                LocalContentColor.current
                            } else {
                                PurpleGrey40
                            },
                            label = ""
                        )
                        Icon(
                            rememberVectorPainter(tab.icon),
                            tint = color,
                            contentDescription = tab.title
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        paddingValues.content()
    }
}

enum class MainTabs(
    val icon: ImageVector,
    val title: String
) {
    LIST(Icons.AutoMirrored.Rounded.List, "List"),
    PROFILE(Icons.Default.Face, "Profile")
}