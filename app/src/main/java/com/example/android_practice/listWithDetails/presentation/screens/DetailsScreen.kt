package com.example.android_practice.listWithDetails.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.android_practice.listWithDetails.data.repository.DogsRepository
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.ui.Spacing
import com.example.android_practice.ui.components.EmptyDataBox
import com.example.android_practice.ui.components.SimpleAppBar
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.StackNavContainer
import kotlinx.parcelize.Parcelize


@Parcelize
class DetailsScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
    val dogId: String
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val dog by remember {
            mutableStateOf(DogsRepository().getById(dogId))
        }

        DogScreenContent(dog = dog, LocalStackNavigation.current )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun DogScreenContent(
    dog: DogFullEntity?,
    navigation: StackNavContainer? = null,
) {
    Scaffold (
        topBar = { SimpleAppBar(dog?.name.orEmpty(), navigation) },
    ) {
        dog ?: run {
            EmptyDataBox("Такая собака не найдена")
            return@Scaffold
        }

        Column(
            Modifier
                .padding(it)
                .padding(Spacing.medium)) {
            Row{
                AsyncImage(
                    model = dog.image  ?: "нет изображения", contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(Spacing.medium)
                )

                Column (Modifier.weight(1f)) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = "Выведены для: ${dog.bredFor  ?: "неизвестно"} ",
                        style = MaterialTheme.typography.titleSmall,
                    )

                    Text(
                        text = "Страна происхождения: ${dog.origin  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                    )

                    Text(
                        text = "Группа пород: ${dog.breedGroup  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                    )

                    Text(
                        text = "Продолжительность жизни: ${dog.lifeSpan  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                    )

                    Text(
                        text = "Характер: ${dog.temperament  ?: "неизвестно"}",
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }

            Row {
                Text(
                    text = dog.description ?: "ывывывывыв",
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }


}

@Preview
@Composable
private fun DogScreenContentPreview() {
    DogsRepository().getById("1")?.let {
        DogScreenContent(it)
    }
}