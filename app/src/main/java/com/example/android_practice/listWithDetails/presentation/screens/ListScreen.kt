package com.example.android_practice.listWithDetails.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_practice.R
import com.example.android_practice.listWithDetails.data.mock.DogsData
import com.example.android_practice.listWithDetails.presentation.viewsModel.ListViewModel
import com.example.android_practice.ui.Spacing
import com.example.android_practice.ui.components.EmptyDataBox
import com.example.android_practice.ui.components.FullscreenLoading
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {

        var navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<ListViewModel>{ parametersOf(navigation) }
        val state = viewModel.viewState

        Scaffold(
            topBar = {
                TextField(
                    value = state.query,
                    onValueChange = { viewModel.onQueryChanged(it) },

                    label = { Text(stringResource(R.string.search)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small),
                    leadingIcon = { Icon(Icons.Rounded.Search, null) },
                )
            },
            contentWindowInsets = WindowInsets(0.dp),
        ) {
            if (state.isLoading) {
                FullscreenLoading()
                return@Scaffold
            }

            state.error?.let {
                EmptyDataBox(msg = it)
                return@Scaffold
            }
            if (state.isEmpty) {
                EmptyDataBox("Такая собака не найдена")
                return@Scaffold
            }

            LazyColumn(Modifier.padding(it)) {
                items(state.items) {
                    DogItem(
                        item = it,
                        Modifier.clickable { viewModel.onItemClicked(it.name) }
                    )
                }
            }
            }
    }
}


@Preview(showBackground = true)
@Composable
fun DogItemPreview(){
    DogItem(item = DogsData.dogsShortEntity.first())
}

