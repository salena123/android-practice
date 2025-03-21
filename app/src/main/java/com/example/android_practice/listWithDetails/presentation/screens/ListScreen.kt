package com.example.android_practice.listWithDetails.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.android_practice.R
import com.example.android_practice.listWithDetails.data.repository.DogsRepository
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity
import com.example.android_practice.ui.Spacing
import com.example.android_practice.ui.components.EmptyDataBox
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize

@Parcelize
class ListScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        var items by rememberSaveable {
            mutableStateOf(DogsRepository().getList())
        }

        var search by rememberSaveable { mutableStateOf("") }

        var navigation = LocalStackNavigation.current

        Scaffold(
            topBar = {
                TextField(
                    value = search,
                    onValueChange = {
                        search = it
                        items = DogsRepository().getList(search)
                    },
                    label = { Text(stringResource(R.string.search)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.small),
                    leadingIcon = { Icon(Icons.Rounded.Search, null) },
                )
            },
            contentWindowInsets = WindowInsets(0.dp),
        ) {
            if (items.isEmpty()) {
                EmptyDataBox("Такая собака не найдена")
            }

            LazyColumn(Modifier.padding(it)) {
                items(items) {
                    DogItem(
                        item = it,
                        Modifier.clickable { navigation.forward(DetailsScreen(dogId = it.id)) }
                    )
                }
            }
            }
    }
}

@Composable
fun DogItem(
    item: DogShortEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(Spacing.medium)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.image ?: "без имени",
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(Spacing.medium))

        Column{
            Text(
                text = item.name ,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${item.temperament} ",
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogItemPreview(){
    DogItem(item = DogsRepository().getList().first() )
}

